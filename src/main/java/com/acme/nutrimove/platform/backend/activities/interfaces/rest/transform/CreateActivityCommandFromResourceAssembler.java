package com.acme.nutrimove.platform.backend.activities.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.activities.domain.model.commands.CreateActivityCommand;
import com.acme.nutrimove.platform.backend.activities.interfaces.rest.resources.CreateActivityResource;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import org.springframework.stereotype.Component;

@Component
public class CreateActivityCommandFromResourceAssembler {

    public CreateActivityCommand toCommand(CreateActivityResource resource, User user) {
        return new CreateActivityCommand(
                resource.name(),
                resource.description(),
                resource.duration(),
                user.getId()
        );
    }
}
