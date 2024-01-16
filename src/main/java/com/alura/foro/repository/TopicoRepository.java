package com.alura.foro.repository;

import com.alura.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {


    Page<Topico> findByActiveTrue(Pageable pageable);
}