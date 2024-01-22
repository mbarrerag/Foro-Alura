package com.alura.foro.infra.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The SecurityConfiguration class configures the security settings for the application.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * The SecurityFilter instance for handling security-related tasks.
     */
    private final SecurityFilter securityFilter;

    /**
     * Constructs a SecurityConfiguration with the provided SecurityFilter.
     *
     * @param securityFilter The SecurityFilter instance.
     */
    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    /**
     * Configures the security filter chain for the application.
     *
     * @param httpSecurity The HttpSecurity instance.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Configuring Spring to use stateless session management
        return httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Creates an AuthenticationManager bean.
     *
     * @param authenticationConfiguration The AuthenticationConfiguration instance.
     * @return The AuthenticationManager bean.
     * @throws Exception If an error occurs during bean creation.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Creates a PasswordEncoder bean using BCrypt.
     *
     * @return The PasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
