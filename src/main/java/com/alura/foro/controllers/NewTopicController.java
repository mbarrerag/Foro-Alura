package com.alura.foro.controllers;


import com.alura.foro.records.DataNewTopic;
import com.alura.modelo.Topico;
import com.alura.foro.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newtopics")
public class NewTopicController {


    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void newTopic(@RequestBody DataNewTopic dataNewTopic){
        System.out.println("new topic");
        System.out.println(dataNewTopic.author());
        topicoRepository.save(new Topico(dataNewTopic));
    }
}
