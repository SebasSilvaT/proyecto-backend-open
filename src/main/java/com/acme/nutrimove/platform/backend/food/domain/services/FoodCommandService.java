package com.acme.nutrimove.platform.backend.food.domain.services;

import com.acme.nutrimove.platform.backend.food.domain.model.aggregates.Food;
import com.acme.nutrimove.platform.backend.food.domain.model.commands.CreateFoodCommand;
import com.acme.nutrimove.platform.backend.food.domain.model.commands.UpdateFoodCommand;

import java.util.Optional;

public interface FoodCommandService {
    Optional<Food> handle(CreateFoodCommand command);
    Optional<Food> handle(UpdateFoodCommand command);
    void handleDelete(Long id);
}
