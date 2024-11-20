package com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateHydrationResource(LocalDate date, Integer quantity_ml) {
}
