package com.acme.nutrimove.platform.backend.Hydration.domain.model.commands;


public record DeleteHydrationCommand(Long id) {
    public DeleteHydrationCommand {
        if (id == null) throw new IllegalArgumentException("id is null");
    }
}
