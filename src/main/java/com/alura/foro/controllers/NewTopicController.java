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

/**
 * The NewTopicController class handles endpoints related to the creation, retrieval, and update of topics.
 * It provides functionality for creating new topics, retrieving topics, updating topics, and logical deletion of topics.
 *
 */
@RestController
@RequestMapping("/newtopics")
@EnableJpaRepositories(basePackages = "com.alura.foro.repository")
@EntityScan(basePackageClasses = Topico.class)
@SecurityRequirement(name = "bearer-key")
public class NewTopicController {

    private final TopicoRepository topicoRepository;
    private final UserRepository userRepository;
    private final CurseRepository curseRepository;

    /**
     * Constructs an instance of NewTopicController.
     *
     * @param topicoRepository The repository for managing topics.
     * @param userRepository   The repository for managing users.
     * @param curseRepository  The repository for managing courses.
     */
    public NewTopicController(TopicoRepository topicoRepository, UserRepository userRepository, CurseRepository curseRepository) {
        this.topicoRepository = topicoRepository;
        this.userRepository = userRepository;
        this.curseRepository = curseRepository;
    }

    /**
     * Creates a new topic based on the provided data.
     *
     * @param dataNewTopic        The data for the new topic.
     * @param uriComponentsBuilder The URI components builder for constructing the response URI.
     * @return ResponseEntity containing the information of the created topic.
     */
    @PostMapping
    public ResponseEntity<UpdateAnswerDataTopic> newTopic(@RequestBody @Valid DataNewTopic dataNewTopic, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = userRepository.getById(dataNewTopic.author());
        Curso curso = curseRepository.getById(dataNewTopic.curse());

        if ((usuario != null && curso != null)) {
            Topico topico = topicoRepository.save(new Topico(dataNewTopic, usuario, curso));
            UpdateAnswerDataTopic updateAnswerDataTopic = new UpdateAnswerDataTopic(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getCurso().getId());

            URI url = uriComponentsBuilder.path("/newtopics/all/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(updateAnswerDataTopic);
        } else {
            System.out.println("usuario no encontrado");
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a page of topics with pagination.
     *
     * @param pageable The pagination parameters.
     * @return Page of GetTopicRepository representing the retrieved topics.
     */
    @GetMapping("/all")
    public Page<GetTopicRepository> getTopics(@PageableDefault(size = 2) Pageable pageable) {
        return topicoRepository.findByActiveTrue(pageable).map(GetTopicRepository::new);
    }

    /**
     * Retrieves information about a specific topic based on its ID.
     *
     * @param id The ID of the topic.
     * @return ResponseEntity containing the information of the retrieved topic.
     */
    @GetMapping("/all/{id}")
    public ResponseEntity<UpdateAnswerDataTopic> getTopics(@PathVariable Long id) {
        Topico topico = topicoRepository.getById(id);
        if (topico != null) {
            var datosTopico = new UpdateAnswerDataTopic(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getCurso().getId());
            return ResponseEntity.ok(datosTopico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates the information of a specific topic based on the provided data.
     *
     * @param updateTopic The data for updating the topic.
     * @return ResponseEntity containing the updated information of the topic.
     */
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

        return ResponseEntity.ok(new UpdateAnswerDataTopic(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getCurso().getId()));
    }

    /**
     * Logically deletes a specific topic based on its ID.
     *
     * @param id The ID of the topic to be deleted.
     * @return ResponseEntity indicating the success or failure of the deletion operation.
     */
    @Transactional
    @DeleteMapping("/all/deleted/{id}")
    public ResponseEntity<Topico> deletedTopic(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);

        if (topico.getActive() == true) {
            topico.desactiveTopic();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
