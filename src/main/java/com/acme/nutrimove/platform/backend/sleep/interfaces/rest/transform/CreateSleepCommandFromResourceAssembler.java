package com.acme.nutrimove.platform.backend.sleep.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.CreateSleepCommand;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources.CreateSleepResource;
import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep.Quality;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

public class CreateSleepCommandFromResourceAssembler {

    public static CreateSleepCommand toCommand(CreateSleepResource resource, User user) {
        return new CreateSleepCommand(
                user,
                resource.date(),
                resource.hoursSlept(),
                Quality.valueOf(resource.quality().toUpperCase())
        );
    }
}