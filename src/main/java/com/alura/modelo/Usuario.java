package com.alura.modelo;

import com.alura.foro.records.UserNew;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * The Usuario class represents a user entity in the application.
 *
 * Implements UserDetails for integration with Spring Security.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "user")
@Getter
@AllArgsConstructor
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nombre;

	@NotBlank
	private String email;

	@NotBlank
	private String phone;

	@NotBlank
	private String contrasena;

	/**
	 * Default constructor for Usuario.
	 */
	public Usuario() {
	}

	/**
	 * Constructor for creating a new Usuario based on provided UserNew data.
	 *
	 * @param dataUser The data for the new user.
	 */
	public Usuario(UserNew dataUser) {
		this.nombre = dataUser.nombre();
		this.email = dataUser.email();
		this.contrasena = dataUser.contrasena();
		this.phone = dataUser.phoneNumber();
	}

	// Getter and Setter methods for attributes

	// Overrides for hashCode, equals, and other methods...

	/**
	 * Retrieves the user by their ID.
	 *
	 * @param id The ID of the user.
	 * @return The user.
	 */
	public Usuario getById(Long id) {
		return this;
	}

	/**
	 * Gets the user's authorities (roles).
	 *
	 * @return The list of granted authorities (roles).
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	// Methods from UserDetails interface

	@Override
	public String getPassword() {
		return contrasena;
	}

	@Override
	public String getUsername() {
		return nombre;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
