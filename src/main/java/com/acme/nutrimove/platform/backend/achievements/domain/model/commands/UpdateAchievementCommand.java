package com.acme.nutrimove.platform.backend.achievements.domain.model.commands;

import java.time.LocalDate;

public record UpdateAchievementCommand(
        Long achievementId,
        Long userId,
        String achievement,
        LocalDate date
) {
    public UpdateAchievementCommand {
        if (achievementId == null) throw new IllegalArgumentException("achievementId cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
        if (achievement == null || achievement.isBlank()) throw new IllegalArgumentException("achievement cannot be null or empty");
        if (date == null) throw new IllegalArgumentException("date cannot be null");
    }
}
