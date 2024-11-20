package com.acme.nutrimove.platform.backend.Hydration.domain.model.queries;

public record GetHydrationById(Long id) {

    public GetHydrationById {
        if (id == null) throw new IllegalArgumentException("id is null");
    }
}
