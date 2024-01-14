package com.alura.foro.records;

import com.alura.modelo.Curso;
import jakarta.validation.constraints.NotNull;

public record DataNewTopic( String tittle, String menssage, Long author, Long curse){
}
