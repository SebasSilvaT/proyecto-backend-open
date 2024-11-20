package com.acme.nutrimove.platform.backend.achievements.domain.services;

import com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates.Achievement;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.CreateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.UpdateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.DeleteAchievementCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

import java.util.Optional;

public interface AchievementCommandService {
    Optional<Achievement> handle(CreateAchievementCommand command, User user);
    Optional<Achievement> handle(UpdateAchievementCommand command);
    void handle(DeleteAchievementCommand command);
}