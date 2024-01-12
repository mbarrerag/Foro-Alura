package com.alura.foro.repository;


import com.alura.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurseRepository extends JpaRepository<Curso, Long>{

}
