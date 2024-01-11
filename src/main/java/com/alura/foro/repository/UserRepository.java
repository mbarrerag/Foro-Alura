package com.alura.foro.repository;

import com.alura.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}
