package com.acme.nutrimove.platform.backend.sleep.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.UpdateSleepCommand;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources.UpdateSleepResource;
import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep.Quality;

public class UpdateSleepCommandFromResourceAssembler {

    public static UpdateSleepCommand toCommand(Long sleepId, UpdateSleepResource resource) {
        return new UpdateSleepCommand(
                sleepId,
                resource.date(),
                resource.hoursSlept(),
                Quality.valueOf(resource.quality().toUpperCase())
        );
    }
}
