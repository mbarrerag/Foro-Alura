package com.alura.foro.controllers;

import com.alura.foro.records.UserNew;
import com.alura.foro.repository.UserRepository;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newuser")
@EntityScan(basePackageClasses = Usuario.class)
@SecurityRequirement(name = "bearer-key")

public class NewUser {


    private final UserRepository userRepository;
    public NewUser(UserRepository userRepository) {

        this.userRepository = userRepository;

    }


    @PostMapping
    public void newUser(@RequestBody @Valid UserNew dataUser){
        System.out.println("new topic");
        Usuario usuario = new Usuario(dataUser);
        System.out.println( "aa"+usuario.getId());
        userRepository.save(new Usuario(dataUser));

    }
}
