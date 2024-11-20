package com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.CreateHydrationCommand;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources.CreateHydrationResource;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

public class CreateHydrationCommnadFromResourceAssembler {

    public static CreateHydrationCommand toCommand(CreateHydrationResource resource, User user) {
        return new CreateHydrationCommand(resource.date(), resource.quantity_ml(), user);
    }
}
