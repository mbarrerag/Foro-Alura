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

/**
 * The AutentificationController class handles authentication-related endpoints.
 * It provides functionality for user authentication and token generation.
 *
 * @author Miller Barrera
 * @version 1.0
 * @since 2024-01-01
 */
@RestController
@RequestMapping("/login")
public class AutentificationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    /**
     * Constructs an instance of AutentificationController.
     *
     * @param authenticationManager The authentication manager.
     * @param tokenService          The token service for generating JWT tokens.
     */
    public AutentificationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    /**
     * Authenticates a user based on the provided credentials and generates a JWT token.
     *
     * @param dataAutentificationUser The user's authentication data.
     * @return ResponseEntity containing the generated JWT token.
     */
    @PostMapping
    public ResponseEntity autetificationUser(@RequestBody @Valid DataAutentificationUser dataAutentificationUser) {

        Authentication autetificationToken = new UsernamePasswordAuthenticationToken(
                dataAutentificationUser.login(),
                dataAutentificationUser.password()
        );

        var userAuthenticated = authenticationManager.authenticate(autetificationToken);
        var JWTToken = tokenService.generateToken((Usuario) userAuthenticated.getPrincipal());

        return ResponseEntity.ok(new DataJWTtoken(JWTToken));
    }
}
