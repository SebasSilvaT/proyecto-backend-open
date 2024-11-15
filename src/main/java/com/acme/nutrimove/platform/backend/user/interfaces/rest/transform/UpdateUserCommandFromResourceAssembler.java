package com.acme.nutrimove.platform.backend.user.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.user.domain.model.commands.UpdateUserCommand;
import com.acme.nutrimove.platform.backend.user.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {

    public static UpdateUserCommand toCommand(Long userId, UpdateUserResource resource) {
        return new UpdateUserCommand(
                userId,
                resource.name(),
                resource.lastname(),
                resource.email(),
                resource.password(),
                resource.privacy()
        );
    }
}
