package com.acme.nutrimove.platform.backend.Hydration.domain.model.commands;

import java.time.LocalDate;

public record UpdateHydrationCommand(Long id, LocalDate date, Integer quantity_ml) {
}
