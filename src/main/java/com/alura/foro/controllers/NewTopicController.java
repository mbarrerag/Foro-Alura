package com.alura.foro.controllers;


import com.alura.foro.records.DataNewTopic;
import com.alura.foro.records.GetTopicRepository;
import com.alura.foro.records.UpdateTopic;
import com.alura.foro.repository.CurseRepository;
import com.alura.foro.repository.UserRepository;
import com.alura.modelo.Curso;
import com.alura.modelo.Topico;
import com.alura.foro.repository.TopicoRepository;
import com.alura.modelo.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/newtopics")
@EnableJpaRepositories(basePackages = "com.alura.foro.repository")
@EntityScan(basePackageClasses = Topico.class)
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
    public void newTopic(@RequestBody @Valid DataNewTopic dataNewTopic) {
        Usuario usuario = userRepository.getById(dataNewTopic.author());
        Curso curso = curseRepository.getById(dataNewTopic.curse());

        if ((usuario != null && curso != null)) {
            topicoRepository.save(new Topico(dataNewTopic, usuario, curso));
        } else {
            System.out.println("usuario no encontrado");
        }
    }

    @GetMapping("/all")
    public Page<GetTopicRepository> getTopics(@PageableDefault(size = 2) Pageable pageable) {
        return topicoRepository.findAll(pageable).map(GetTopicRepository::new);
    }
//    @GetMapping("/all/{id}")
//    public Optional<GetTopicRepository> getTopics(@PathVariable Long id){
//        return topicoRepository.findAll().stream().filter(topico -> topico.getId().equals(id)).map(GetTopicRepository::new).findFirst();
//    }

    @Transactional
    @PutMapping("/all/update")
    public void updateTopics(@RequestBody @Valid UpdateTopic updateTopic) {

        Topico topico = topicoRepository.getReferenceById(updateTopic.id());

        Usuario usuario;
        Curso curso;
        if (updateTopic.author() != null && updateTopic.curse() != null) {
           usuario = userRepository.getById(updateTopic.author());
        } else {
           usuario = null;
        }
        if(updateTopic.curse() != null){
             curso = curseRepository.getById(updateTopic.curse());
        } else {
            curso = null;
        }
        System.out.println("update topic");
        topico.upDateData(updateTopic, usuario, curso);
    }
}

