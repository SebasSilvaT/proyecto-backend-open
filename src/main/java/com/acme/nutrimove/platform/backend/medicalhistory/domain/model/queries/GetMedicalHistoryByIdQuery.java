package com.acme.nutrimove.platform.backend.medicalhistory.domain.model.queries;

public record GetMedicalHistoryByIdQuery(Long id, Long userId) {
    public GetMedicalHistoryByIdQuery {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
    }
}
