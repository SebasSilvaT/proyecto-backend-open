package com.acme.nutrimove.platform.backend.food.domain.model.commands;

public record UpdateFoodCommand(
        Long id,
        String name,
        int calories,
        int proteins,
        int carbs,
        int fats
) {
}
