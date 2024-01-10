package com.alura.foro.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages =
		"com.alura.modelo")

public class ForoInicialApplication {


	public static void main(String[] args) {
		SpringApplication.run(ForoInicialApplication.class, args);
	}

}
