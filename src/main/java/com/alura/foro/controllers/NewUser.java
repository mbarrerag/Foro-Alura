package com.alura.foro.controllers;

import com.alura.foro.records.UserNew;
import com.alura.foro.repository.UserRepository;
import com.alura.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newuser")
public class NewUser {

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public void newTopic(@RequestBody UserNew dataUser){
        System.out.println("new topic");
        System.out.println(dataUser.name());
        userRepository.save(new Usuario(dataUser));

    }
}
