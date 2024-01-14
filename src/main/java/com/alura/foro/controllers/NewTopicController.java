package com.alura.foro.controllers;


import com.alura.foro.records.DataNewTopic;
import com.alura.foro.repository.CurseRepository;
import com.alura.foro.repository.UserRepository;
import com.alura.modelo.Curso;
import com.alura.modelo.Topico;
import com.alura.foro.repository.TopicoRepository;
import com.alura.modelo.Usuario;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void newTopic(@RequestBody @Valid DataNewTopic dataNewTopic){
        Usuario usuario = userRepository.getById(dataNewTopic.author());
        Curso curso = curseRepository.getById(dataNewTopic.curse());
        System.out.println( "aa"+curso.getNombre());

        topicoRepository.save(new Topico(dataNewTopic, usuario, curso));

    }

    @GetMapping
    public List<Topico> getTopics(){
        return topicoRepository.findAll();
    }
}
