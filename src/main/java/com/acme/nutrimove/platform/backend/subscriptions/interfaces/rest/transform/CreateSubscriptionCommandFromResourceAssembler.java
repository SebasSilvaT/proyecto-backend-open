package com.acme.nutrimove.platform.backend.subscriptions.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.acme.nutrimove.platform.backend.subscriptions.interfaces.rest.resources.CreateSubscriptionResource;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommand(CreateSubscriptionResource resource, User user) {
        return new CreateSubscriptionCommand(resource.description(), resource.price(), resource.monthDuration(),
                resource.trial(), user);
    }
}
