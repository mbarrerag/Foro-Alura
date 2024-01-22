package com.alura.modelo;

import com.alura.foro.records.CourseNewTopic;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The Curso class represents a course entity in the application.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "course")
@Getter
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nombre;

	@NotBlank
	private String categoria;

	/**
	 * Constructs a Curso object based on the provided CourseNewTopic.
	 *
	 * @param curse The CourseNewTopic object containing course details.
	 */
	public Curso(CourseNewTopic curse) {
		this.nombre = curse.name();
		this.categoria = curse.category();
	}

	/**
	 * Default constructor for Curso.
	 */
	public Curso() {
	}

	// hashCode, equals, setId, getId, and getCurseById methods...

}
