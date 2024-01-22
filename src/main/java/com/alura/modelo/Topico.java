package com.alura.modelo;

import com.alura.foro.repository.CurseRepository;
import com.alura.foro.repository.UserRepository;
import com.alura.foro.records.DataNewTopic;
import com.alura.foro.records.UpdateTopic;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Topico class represents a topic entity in the application.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "topic")
@Getter
@AllArgsConstructor
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotBlank
	private String mensaje;

	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;

	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "autor_id")
	@NotNull
	@Valid
	private Usuario autor;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	@NotNull
	@Valid
	private Curso curso;

	/**
	 * Default constructor for Topico.
	 */
	public Topico() {
	}

	/**
	 * Constructor for creating a new Topico based on provided DataNewTopic, Usuario, and Curso.
	 *
	 * @param dataNewTopic The data for the new topic.
	 * @param usuario      The user associated with the topic.
	 * @param curso        The course associated with the topic.
	 */
	public Topico(DataNewTopic dataNewTopic, Usuario usuario, Curso curso) {
		this.active = true;
		this.titulo = dataNewTopic.tittle();
		this.mensaje = dataNewTopic.menssage();
		this.curso = curso;
		this.autor = usuario;
	}

	// Getter and Setter methods for attributes

	/**
	 * Deactivates the topic, marking it as inactive.
	 */
	public void desactiveTopic() {
		this.active = false;
	}

	/**
	 * Updates the topic data based on the provided UpdateTopic, Usuario, and Curso.
	 *
	 * @param updateTopic The data for updating the topic.
	 * @param usuario     The user associated with the topic.
	 * @param curso       The course associated with the topic.
	 */
	public void upDateData(UpdateTopic updateTopic, Usuario usuario, Curso curso) {
		// Implementation details...
	}

	// Other methods...

	// Overrides for hashCode, equals, and other methods...

}
