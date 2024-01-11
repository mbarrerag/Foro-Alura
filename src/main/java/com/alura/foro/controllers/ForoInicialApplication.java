package com.alura.foro.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.alura.modelo"})
@ComponentScan(basePackages = "com.alura")

public class ForoInicialApplication {


	public static void main(String[] args) {
		SpringApplication.run(ForoInicialApplication.class, args);
	}

}
