package com.acme.nutrimove.platform.backend.food.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.food.domain.model.commands.UpdateFoodCommand;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.resources.UpdateFoodResource;

public class UpdateFoodCommandFromResourceAssembler {
    public static UpdateFoodCommand toCommand(Long id, UpdateFoodResource resource) {
        return new UpdateFoodCommand(
                id,
                resource.name(),
                resource.calories(),
                resource.proteins(),
                resource.carbs(),
                resource.fats()
        );
    }
}
