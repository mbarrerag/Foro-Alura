package com.alura.foro.controllers;


import com.alura.foro.newtopic.DataNewTopic;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newtopics")
public class NewTopicController {

    @PostMapping
    public void newTopic(@RequestBody DataNewTopic dataNewTopic){
        System.out.println("new topic");
        System.out.println(dataNewTopic);
    }
}
