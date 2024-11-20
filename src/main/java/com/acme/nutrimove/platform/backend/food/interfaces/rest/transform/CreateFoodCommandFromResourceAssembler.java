package com.acme.nutrimove.platform.backend.food.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.food.domain.model.commands.CreateFoodCommand;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.resources.CreateFoodResource;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

public class CreateFoodCommandFromResourceAssembler {
    public static CreateFoodCommand toCommand(CreateFoodResource resource, User user) {
        return new CreateFoodCommand(
                user,
                resource.name(),
                resource.calories(),
                resource.proteins(),
                resource.carbs(),
                resource.fats()
        );
    }
}
