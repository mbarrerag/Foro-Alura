package com.alura.foro.infra.Security;

import com.alura.modelo.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")

    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Foro-Alura").withSubject(usuario.getEmail()).withClaim("id", usuario.getId())
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token");
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Error al decodificar el token");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
             verifier = JWT.require(algorithm).withIssuer("Foro-Alura")
                    .build().verify(token);


        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al decodificar el token", exception);
        }
        if (verifier == null) {
            throw new RuntimeException("Error al decodificar el token");
        }
        System.out.println("Token Before Verification: " + token);
        System.out.println("subject"+verifier.getSubject());

        return verifier.getSubject();
    }
}