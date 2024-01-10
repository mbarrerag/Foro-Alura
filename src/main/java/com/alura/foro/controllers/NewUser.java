package com.alura.foro.controllers;

import com.alura.foro.newtopic.DataNewTopic;
import com.alura.foro.newtopic.UserNew;
import com.alura.modelo.Topico;
import com.alura.modelo.TopicoRepository;
import com.alura.modelo.UserRepository;
import com.alura.modelo.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newuser")
public class NewUser {
    private UserRepository userRepository;


    @PostMapping
    public void newTopic(@RequestBody UserNew dataUser){
        System.out.println("new topic");
        System.out.println(dataUser.name());
        userRepository.save(new Usuario(dataUser));

    }
}
