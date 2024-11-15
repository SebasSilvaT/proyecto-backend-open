package com.acme.nutrimove.platform.backend.activities.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.activities.domain.model.aggregates.Activity;
import com.acme.nutrimove.platform.backend.activities.interfaces.rest.resources.ActivityResource;

public class ActivityResourceFromEntityAssembler {

    public static ActivityResource toResourceFromEntity(Activity activity) {
        return new ActivityResource(
                activity.getId(),
                activity.getName(),
                activity.getDescription(),
                activity.getDuration(),
                activity.getUserId()
        );
    }
}
