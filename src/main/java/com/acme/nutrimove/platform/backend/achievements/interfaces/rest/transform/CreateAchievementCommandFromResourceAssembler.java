package com.acme.nutrimove.platform.backend.achievements.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.CreateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources.CreateAchievementResource;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import org.springframework.stereotype.Component;

@Component
public class CreateAchievementCommandFromResourceAssembler {

    public CreateAchievementCommand toCommand(CreateAchievementResource resource, User user) {
        return new CreateAchievementCommand(
                user.getId(),
                resource.achievement(),
                resource.date()
        );
    }
}