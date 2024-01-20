package com.alura.foro.controllers;


import com.alura.foro.records.DataNewTopic;
import com.alura.foro.records.GetTopicRepository;
import com.alura.foro.records.UpdateAnswerDataTopic;
import com.alura.foro.records.UpdateTopic;
import com.alura.foro.repository.CurseRepository;
import com.alura.foro.repository.UserRepository;
import com.alura.modelo.Curso;
import com.alura.modelo.Topico;
import com.alura.foro.repository.TopicoRepository;
import com.alura.modelo.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/newtopics")
@EnableJpaRepositories(basePackages = "com.alura.foro.repository")
@EntityScan(basePackageClasses = Topico.class)
@SecurityRequirement(name = "bearer-key")
public class NewTopicController {


    private final TopicoRepository topicoRepository;
    private final UserRepository userRepository;
    private final CurseRepository curseRepository;

    // Constructor para inyectar TopicoRepository
    public NewTopicController(TopicoRepository topicoRepository, UserRepository userRepository, CurseRepository curseRepository) {
        this.topicoRepository = topicoRepository;
        this.userRepository = userRepository;
        this.curseRepository = curseRepository;
    }

    @PostMapping
    public ResponseEntity<UpdateAnswerDataTopic> newTopic(@RequestBody @Valid DataNewTopic dataNewTopic, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = userRepository.getById(dataNewTopic.author());
        Curso curso = curseRepository.getById(dataNewTopic.curse());

        if ((usuario != null && curso != null)) {
            Topico topico = topicoRepository.save(new Topico(dataNewTopic, usuario, curso));
            UpdateAnswerDataTopic updateAnswerDataTopic = new UpdateAnswerDataTopic(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getCurso().getId());

//
//            URI url = URI.create("http://localhost:8080/newtopics/all/" + topico.getId());
            URI url = uriComponentsBuilder.path("/newtopics/all/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(updateAnswerDataTopic);
        } else {
            System.out.println("usuario no encontrado");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public Page<GetTopicRepository> getTopics(@PageableDefault(size = 2) Pageable pageable) {
        /* Form to make a query implicit into the Repository*/
        return topicoRepository.findByActiveTrue(pageable).map(GetTopicRepository::new);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<UpdateAnswerDataTopic> getTopics(@PathVariable Long id){
        Topico topico = topicoRepository.getById(id);
        if(topico != null){
            var datosTopico = new UpdateAnswerDataTopic(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getCurso().getId());
            return ResponseEntity.ok(datosTopico);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PutMapping("/all/update")
    public ResponseEntity<UpdateAnswerDataTopic> updateTopics(@RequestBody @Valid UpdateTopic updateTopic) {

        Topico topico = topicoRepository.getReferenceById(updateTopic.id());

        Usuario usuario;
        Curso curso;
        if (updateTopic.author() != null && updateTopic.curse() != null) {
            usuario = userRepository.getById(updateTopic.author());
        } else {
            usuario = null;
        }
        if (updateTopic.curse() != null) {
            curso = curseRepository.getById(updateTopic.curse());
        } else {
            curso = null;
        }
        System.out.println("update topic");
        topico.upDateData(updateTopic, usuario, curso);
        /*Is not recommendable return the entity so we create a DTO and return his values*/
        return ResponseEntity.ok(new UpdateAnswerDataTopic(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getCurso().getId()));
    }


    /*Logical deleted*/
    @Transactional
    @DeleteMapping("/all/deleted/{id}")
    public ResponseEntity<Topico> deletedTopic(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        /*Logic deleted*/
        if (topico.getActive() == true) {
            topico.desactiveTopic();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();

        }
    }
//      Physical deleted
//    @Transactional
//    @DeleteMapping("/all/deleted")
//    public void deletedTopic(@RequestBody @Valid UpdateTopic updateTopic){
//        Topico topico = topicoRepository.getReferenceById(updateTopic.id());
//        topicoRepository.delete(topico);
//    }

}

