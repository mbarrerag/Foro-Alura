package com.alura.foro.infra.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader("Authorization");
        if (token == null || token.isBlank()) {
            throw new RuntimeException("Token is null");
        }
        token = token.replace("Bearer", "");
        System.out.println(token);
        System.out.println(tokenService.getSubject(token));
        filterChain.doFilter(request,response);
    }
}
