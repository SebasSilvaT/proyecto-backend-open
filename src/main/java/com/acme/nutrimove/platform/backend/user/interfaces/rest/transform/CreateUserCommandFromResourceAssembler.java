package com.acme.nutrimove.platform.backend.user.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.user.domain.model.commands.CreateUserCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User; // Importa User para acceder a Privacy
import com.acme.nutrimove.platform.backend.user.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommand(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.name(),
                resource.lastname(),
                resource.email(),
                resource.password(),
                User.Privacy.valueOf(resource.privacy().toUpperCase()) // Usar User.Privacy aquí
        );
    }
}
