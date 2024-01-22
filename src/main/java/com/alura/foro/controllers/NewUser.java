package com.alura.foro.controllers;

import com.alura.foro.records.UserNew;
import com.alura.foro.repository.UserRepository;
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

/**
 * The NewUser class handles endpoints related to the creation of new users.
 * It provides functionality for creating a new user with the provided user data.
 *
 */
@RestController
@RequestMapping("/newuser")
@EntityScan(basePackageClasses = Usuario.class)
@SecurityRequirement(name = "bearer-key")
public class NewUser {

    private final UserRepository userRepository;

    /**
     * Constructs an instance of NewUser.
     *
     * @param userRepository The repository for managing users.
     */
    @Autowired
    public NewUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user based on the provided user data.
     *
     * @param dataUser The data for the new user.
     */
    @PostMapping
    public void newUser(@RequestBody @Valid UserNew dataUser) {
        System.out.println("new topic");
        Usuario usuario = new Usuario(dataUser);
        System.out.println("aa" + usuario.getId());
        userRepository.save(new Usuario(dataUser));
    }
}
