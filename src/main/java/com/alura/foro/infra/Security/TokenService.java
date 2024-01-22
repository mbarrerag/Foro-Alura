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

/**
 * The TokenService class provides methods for generating and decoding JWT tokens used for
 * user authentication and authorization in the application.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
@Service
public class TokenService {

    /**
     * The secret key used for signing and verifying JWT tokens.
     */
    @Value("123456")
    private String secret;

    /**
     * Generates a JWT token for the provided user.
     *
     * @param usuario The Usuario (user) for whom the token is generated.
     * @return A JWT token as a String.
     * @throws RuntimeException If an error occurs during token creation.
     */
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

    /**
     * Calculates the expiration time for the JWT token (2 hours from the current time).
     *
     * @return The expiration time as an Instant.
     */
    private Instant getExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    /**
     * Decodes and verifies the provided JWT token, returning the subject (user's email).
     *
     * @param token The JWT token to be decoded and verified.
     * @return The subject (user's email) extracted from the token.
     * @throws RuntimeException If an error occurs during token decoding or verification.
     */
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
        System.out.println("subject" + verifier.getSubject());

        return verifier.getSubject();
    }
}
