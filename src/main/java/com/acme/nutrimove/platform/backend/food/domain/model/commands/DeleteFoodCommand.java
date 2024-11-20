package com.acme.nutrimove.platform.backend.food.domain.model.commands;

public record DeleteFoodCommand(Long foodId) {
    public DeleteFoodCommand {
        if (foodId == null) throw new IllegalArgumentException("Food ID cannot be null");
    }
}
