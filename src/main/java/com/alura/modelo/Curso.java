package com.alura.modelo;

import com.alura.foro.records.CourseNewTopic;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "course")
@Getter

public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;





	public Curso( CourseNewTopic curse ) {
		this.nombre = curse.name();
		this.categoria = curse.category();

	}

	public Curso() {

	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Curso getCurseById( Long id) {
		return this;
	}
}

