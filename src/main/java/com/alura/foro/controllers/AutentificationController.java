package com.alura.foro.controllers;


import com.alura.foro.infra.Security.DataJWTtoken;
import com.alura.foro.infra.Security.TokenService;
import com.alura.foro.records.DataAutentificationUser;
import com.alura.modelo.Usuario;
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
    private final TokenService tokenService;

    public AutentificationController( AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @PostMapping
    public ResponseEntity autetificationUser(@RequestBody @Valid DataAutentificationUser dataAutentificationUser) {

        Authentication Autetificationtoken = new UsernamePasswordAuthenticationToken(dataAutentificationUser.login(),
                dataAutentificationUser.password());
                var userAuthentificated = authenticationManager.authenticate(Autetificationtoken);
                var JWTtoken = tokenService.generateToken((Usuario) userAuthentificated.getPrincipal());
                return ResponseEntity.ok(new DataJWTtoken(JWTtoken));
    }
}
