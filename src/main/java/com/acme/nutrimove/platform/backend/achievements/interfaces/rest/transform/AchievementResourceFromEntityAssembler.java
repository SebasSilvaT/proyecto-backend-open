package com.acme.nutrimove.platform.backend.achievements.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates.Achievement;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources.AchievementResource;
import org.springframework.stereotype.Component;

@Component
public class AchievementResourceFromEntityAssembler {

    public AchievementResource toResourceFromEntity(Achievement entity) {
        return new AchievementResource(
                entity.getId(),
                entity.getUser() != null ? entity.getUser().getId() : null,
                entity.getAchievement(),
                entity.getDate()
        );
    }
}