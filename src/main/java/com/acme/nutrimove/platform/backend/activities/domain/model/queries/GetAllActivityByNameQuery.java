package com.acme.nutrimove.platform.backend.activities.domain.model.queries;

public record GetAllActivityByNameQuery(String name) {
    public GetAllActivityByNameQuery {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
    }
}
