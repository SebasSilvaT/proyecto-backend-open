package com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands;

import java.time.LocalDate;

public record CreateMedicalHistoryCommand(
        Long userId,
        LocalDate date,
        String condition,      // Campo `condition` agregado
        String description
) {
    public CreateMedicalHistoryCommand {
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        if (condition == null || condition.isBlank()) throw new IllegalArgumentException("condition cannot be null or empty");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("description cannot be null or empty");
    }
}
