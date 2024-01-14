package com.alura.modelo;

import com.alura.foro.records.DataNewTopic;
import com.alura.foro.repository.CurseRepository;
import com.alura.foro.repository.UserRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Topico {

	@Id
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
    @ManyToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

//	@OneToMany(mappedBy = "topic")
//	private List<Respuesta> respuestas = new ArrayList<>();


	public Topico(DataNewTopic dataNewTopic, Usuario usuario, Curso curso) {
		this.id = dataNewTopic.id();
		this.titulo = dataNewTopic.tittle();
		this.mensaje = dataNewTopic.menssage();
		this.curso = curso;
		this.autor = usuario;
	}

	private Usuario getUserById(Long userId, UserRepository usuarioRepository) {
		return usuarioRepository.findById(userId).orElse(null);
	}


	private Curso getCourseById(Long cursoId, CurseRepository cursoRepository) {
		return cursoRepository.findById(cursoId).orElse(null);
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
		Topico other = (Topico) obj;
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
}
