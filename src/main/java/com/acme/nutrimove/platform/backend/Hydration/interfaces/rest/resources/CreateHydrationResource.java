package com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateHydrationResource(LocalDate date, Integer quantity_ml, Long userId) {

    public CreateHydrationResource {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        if (quantity_ml == null) throw new IllegalArgumentException("quantity_ml cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
    }
}
