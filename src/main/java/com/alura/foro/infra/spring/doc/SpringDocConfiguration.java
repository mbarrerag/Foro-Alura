package com.alura.foro.infra.spring.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The SpringDocConfiguration class provides custom OpenAPI configuration for Swagger documentation.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
@Configuration
public class SpringDocConfiguration {

    /**
     * Configures custom OpenAPI settings for Swagger documentation.
     *
     * @return An OpenAPI object with custom settings.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    /**
     * A sample method for testing the SpringDocConfiguration.
     */
    @Bean
    public void message(){
        System.out.println("SpringDocConfiguration.message()");
    }
}
