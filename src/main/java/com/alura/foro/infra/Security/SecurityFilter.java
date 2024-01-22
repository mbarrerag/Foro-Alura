package com.alura.foro.infra.Security;

import com.alura.foro.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * The SecurityFilter class is responsible for intercepting and processing incoming requests
 * to extract and validate the authorization token, and then setting the user's authentication
 * information in the security context.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    /**
     * The UserRepository instance for querying user details.
     */
    private final UserRepository userRepository;

    /**
     * The TokenService instance for handling JWT token operations.
     */
    private final TokenService tokenService;

    /**
     * Constructs a SecurityFilter with the provided TokenService and UserRepository.
     *
     * @param tokenService    The TokenService instance.
     * @param userRepository  The UserRepository instance.
     */
    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    /**
     * Performs the actual filter logic for each incoming request.
     *
     * @param request     The HttpServletRequest instance.
     * @param response    The HttpServletResponse instance.
     * @param filterChain The FilterChain for executing the next filters in the chain.
     * @throws ServletException If a servlet-related exception occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);
            if (subject != null) {
                // Force session initiation
                var usuario = userRepository.findByNombre(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
