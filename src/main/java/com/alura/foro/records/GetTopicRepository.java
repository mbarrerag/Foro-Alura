package com.alura.foro.records;

import com.alura.modelo.Topico;

import java.time.LocalDateTime;
import java.util.Date;

/*Use to set the data that we will receive from the get HTTP method*/
public record GetTopicRepository(Long id, String titulo, String mensaje, LocalDateTime fecha_creacion, String status, Long autor, Long curso) {

    public GetTopicRepository(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus().name(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}
