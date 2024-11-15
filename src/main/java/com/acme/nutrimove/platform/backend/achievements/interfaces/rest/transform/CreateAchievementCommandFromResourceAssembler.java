package com.acme.nutrimove.platform.backend.achievements.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.CreateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources.CreateAchievementResource;
import org.springframework.stereotype.Component;

@Component
public class CreateAchievementCommandFromResourceAssembler {

    public CreateAchievementCommand toCommand(CreateAchievementResource resource) {
        return new CreateAchievementCommand(
                resource.userId(),
                resource.achievement(),
                resource.date()
        );
    }
}
