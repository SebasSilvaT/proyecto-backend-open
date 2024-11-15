package com.acme.nutrimove.platform.backend.achievements.domain.model.commands;

import java.time.LocalDate;

public record CreateAchievementCommand(
        Long userId,
        String achievement,
        LocalDate date
) {
    public CreateAchievementCommand {
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
        if (achievement == null || achievement.isBlank()) throw new IllegalArgumentException("achievement cannot be null or empty");
        if (date == null) throw new IllegalArgumentException("date cannot be null");
    }
}