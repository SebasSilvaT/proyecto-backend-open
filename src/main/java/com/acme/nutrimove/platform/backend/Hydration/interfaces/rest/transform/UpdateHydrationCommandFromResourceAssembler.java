package com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.UpdateHydrationCommand;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources.UpdateHydrationResource;

public class UpdateHydrationCommandFromResourceAssembler {

    public static UpdateHydrationCommand toCommand(Long id, UpdateHydrationResource resource) {
        return new UpdateHydrationCommand(id, resource.date(), resource.quantity_ml());
    }
}
