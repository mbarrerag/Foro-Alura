package com.alura.foro.records;

import jakarta.validation.constraints.NotNull;

public record UpdateTopic(@NotNull Long id, String tittle, String menssage, Long author, Long curse) {
}
