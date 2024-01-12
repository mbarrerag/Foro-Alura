package com.alura.foro.controllers;


import com.alura.foro.records.DataNewTopic;
import com.alura.modelo.Topico;
import com.alura.foro.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newtopics")
@EnableJpaRepositories(basePackages = "com.alura.foro.repository")
@EntityScan(basePackageClasses = Topico.class)
public class NewTopicController {


    private final TopicoRepository topicoRepository;

    // Constructor para inyectar TopicoRepository
    public NewTopicController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @PostMapping
    public void newTopic(@RequestBody DataNewTopic dataNewTopic){
        System.out.println("new topic");
        System.out.println(dataNewTopic.author());
        topicoRepository.save(new Topico(dataNewTopic));
    }
}
