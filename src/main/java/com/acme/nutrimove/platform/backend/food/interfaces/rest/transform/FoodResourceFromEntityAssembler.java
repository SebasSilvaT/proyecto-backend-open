package com.acme.nutrimove.platform.backend.food.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.food.domain.model.aggregates.Food;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.resources.FoodResource;

public class FoodResourceFromEntityAssembler {
    public static FoodResource toResourceFromEntity(Food food) {
        return new FoodResource(
                food.getId(),
                food.getName(),
                food.getCalories(),
                food.getProteins(),
                food.getCarbs(),
                food.getFats(),
                food.getUser().getId()
        );
    }
}
