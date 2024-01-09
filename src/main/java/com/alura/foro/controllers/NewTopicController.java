package com.alura.foro.controllers;


import com.alura.foro.newtopic.DataNewTopic;
import com.alura.modelo.TopicoRepository;
import com.alura.modelo.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newtopics")
public class NewTopicController {


    @Autowired(required = false)
    private TopicoRepository topicoRepository;

    @PostMapping
    public void newTopic(@RequestBody DataNewTopic dataNewTopic){
        System.out.println("new topic");
        System.out.println(dataNewTopic.author());
        topicoRepository.save(new Topico(dataNewTopic));
    }
}
