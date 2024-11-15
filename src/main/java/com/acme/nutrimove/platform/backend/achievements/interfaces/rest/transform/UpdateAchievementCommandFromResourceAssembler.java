package com.acme.nutrimove.platform.backend.achievements.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.UpdateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources.UpdateAchievementResource;
import org.springframework.stereotype.Component;

@Component
public class UpdateAchievementCommandFromResourceAssembler {

    public UpdateAchievementCommand toCommand(Long id, UpdateAchievementResource resource) {
        return new UpdateAchievementCommand(
                id,
                resource.userId(),
                resource.achievement(),
                resource.date()
        );
    }
}
