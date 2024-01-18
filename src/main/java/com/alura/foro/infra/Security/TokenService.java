package com.alura.foro.infra.Security;

import com.alura.modelo.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
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
                .withIssuer("Foro-Alura").withSubject(usuario.getNombre()).withClaim("id", usuario.getId())
                .withExpiresAt(getExpirationTime())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al crear el token");
        }
    }
    private Instant getExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
