package com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands;

public record DeleteMedicalHistoryCommand(
        Long id,
        Long userId
) {
    public DeleteMedicalHistoryCommand {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
    }
}
