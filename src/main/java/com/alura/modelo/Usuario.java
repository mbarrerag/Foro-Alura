package com.alura.modelo;

import com.alura.foro.records.UserNew;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Entity
@Table(name = "user")
@Getter
@AllArgsConstructor

public class Usuario {

	@Id
	private Long id;
	private String nombre;
	private String email;
	private String contrasena;

	public Usuario() {
	}

	public Usuario(UserNew dataUser) {
		this.id =  dataUser.id();
		this.nombre = dataUser.nombre();
		this.email = dataUser.email();
		this.contrasena = dataUser.contrasena();
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
		Usuario other = (Usuario) obj;
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

	public Usuario getById(Long id) {
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getContrasena() {
		return contrasena;
	}
}
