package com.alura.foro.controllers;


import com.alura.foro.records.DataAutentificationUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutentificationController {

    private final AuthenticationManager authenticationManager;

    public AutentificationController( AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @PostMapping
    public ResponseEntity autetificationUser(@RequestBody @Valid DataAutentificationUser dataAutentificationUser) {

        Authentication token = new UsernamePasswordAuthenticationToken(dataAutentificationUser.login(),
                dataAutentificationUser.password());
                authenticationManager.authenticate(token);
                return ResponseEntity.ok().build();
    }
}
