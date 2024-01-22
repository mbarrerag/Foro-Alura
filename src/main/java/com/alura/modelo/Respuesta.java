package com.alura.modelo;

import java.time.LocalDateTime;

/**
 * The Respuesta class represents an answer or response entity in the application.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
public class Respuesta {

	private Long id;
	private String mensaje;
	private Topico topico;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	private Usuario autor;
	private Boolean solucion = false;

	/**
	 * Default constructor for Respuesta.
	 */
	public Respuesta() {
	}

	/**
	 * Gets the unique identifier of the response.
	 *
	 * @return The unique identifier of the response.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the response.
	 *
	 * @param id The unique identifier to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the message content of the response.
	 *
	 * @return The message content of the response.
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the message content of the response.
	 *
	 * @param mensaje The message content to set.
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	// Similar getter and setter methods for topico, fechaCreacion, autor, and solucion...

}
