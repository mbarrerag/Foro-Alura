package com.alura.foro.controllers;


import com.alura.foro.records.CourseNewTopic;
import com.alura.foro.records.UserNew;
import com.alura.foro.repository.CurseRepository;
import com.alura.modelo.Curso;
import com.alura.modelo.Usuario;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newcourse")
@EntityScan(basePackageClasses = Curso.class)

public class NewCurse {


    private final CurseRepository curseRepository;
    public NewCurse(CurseRepository curseRepository) {
        this.curseRepository = curseRepository;
    }

    @PostMapping
    public void newCourse(@RequestBody @Valid CourseNewTopic dataCourse){
        System.out.println("new topic");
        Curso curso = new Curso(dataCourse);
        System.out.println( "aa"+curso.getId());
        curseRepository.save(new Curso(dataCourse));
    }


}