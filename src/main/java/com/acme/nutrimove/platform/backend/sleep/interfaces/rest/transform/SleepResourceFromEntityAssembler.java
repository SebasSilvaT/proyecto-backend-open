package com.acme.nutrimove.platform.backend.sleep.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources.SleepResource;

public class SleepResourceFromEntityAssembler {

    public static SleepResource toResourceFromEntity(Sleep sleep) {
        return new SleepResource(
                sleep.getId(),
                sleep.getUser() != null ? sleep.getUser().getId() : null,
                sleep.getDate(),
                sleep.getHoursSlept(),
                sleep.getQuality().toString()
        );
    }
}
