package com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.aggregates.Hydration;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources.HydrationResource;

public class HydrationResourceFromEntityAssembler {

    public static HydrationResource toResourceFromEntity(Hydration hydration) {
        return new HydrationResource(hydration.getId(), hydration.getDate(), hydration.getQuantity_ml(),
                hydration.getUserId() != null ? hydration.getUserId().getId() : null);
    }
}
