package com.acme.nutrimove.platform.backend.achievements.domain.services;

import com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates.Achievement;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.CreateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.UpdateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.DeleteAchievementCommand;

import java.util.Optional;

public interface AchievementCommandService {

    Optional<Achievement> handle(CreateAchievementCommand command);

    Optional<Achievement> handle(UpdateAchievementCommand command);

    void handle(DeleteAchievementCommand command);
}
