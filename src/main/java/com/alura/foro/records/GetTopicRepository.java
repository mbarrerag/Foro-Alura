package com.alura.foro.records;

import com.alura.modelo.Topico;

import java.time.LocalDateTime;
import java.util.Date;

/*Use to set the data that we will receive from the get HTTP method*/
public record GetTopicRepository(String titulo, String mensaje, LocalDateTime fecha_creacion, String status, Long autor, Long curso) {

    public GetTopicRepository(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion(), topico.getStatus().name(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}
