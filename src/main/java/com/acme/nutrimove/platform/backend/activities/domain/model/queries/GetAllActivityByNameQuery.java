package com.acme.nutrimove.platform.backend.activities.domain.model.queries;

public record GetAllActivityByNameQuery(String name) {
    public GetAllActivityByNameQuery {
        if (name == null) throw new NullPointerException("name is null");
    }
}
