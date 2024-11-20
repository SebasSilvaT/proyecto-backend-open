package com.acme.nutrimove.platform.backend.food.domain.model.commands;

import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

public record CreateFoodCommand(
        User user,
        String name,
        int calories,
        int proteins,
        int carbs,
        int fats
) {
}
