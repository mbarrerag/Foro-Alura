package com.alura.foro.records;

import jakarta.validation.constraints.NotNull;

public record CourseNewTopic(@NotNull String name, @NotNull String category) {
}
