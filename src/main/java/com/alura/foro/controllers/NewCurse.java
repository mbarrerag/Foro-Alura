package com.alura.foro.controllers;

import com.alura.foro.records.CourseNewTopic;
import com.alura.foro.records.UserNew;
import com.alura.foro.repository.CurseRepository;
import com.alura.modelo.Curso;
import com.alura.modelo.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The NewCurse class handles endpoints related to the creation of new courses.
 * It allows the creation of new courses with the provided data.
 */
@RestController
@RequestMapping("/newcourse")
@EntityScan(basePackageClasses = Curso.class)
@SecurityRequirement(name = "bearer-key")
public class NewCurse {

    private final CurseRepository curseRepository;

    /**
     * Constructs an instance of NewCurse.
     *
     * @param curseRepository The repository for managing courses.
     */
    public NewCurse(CurseRepository curseRepository) {
        this.curseRepository = curseRepository;
    }

    /**
     * Creates a new course based on the provided data.
     *
     * @param dataCourse The data for the new course.
     */
    @PostMapping
    public void newCourse(@RequestBody @Valid CourseNewTopic dataCourse) {
        Curso curso = new Curso(dataCourse);
        curseRepository.save(new Curso(dataCourse));
    }
}
