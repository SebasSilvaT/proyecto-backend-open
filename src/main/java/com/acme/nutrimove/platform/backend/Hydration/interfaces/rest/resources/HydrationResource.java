package com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources;

import java.time.LocalDate;

public record HydrationResource(Long id, LocalDate date, Integer quantity_ml, Long userId) {
}
