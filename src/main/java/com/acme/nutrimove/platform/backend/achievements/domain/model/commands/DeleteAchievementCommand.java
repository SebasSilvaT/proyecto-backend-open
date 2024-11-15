package com.acme.nutrimove.platform.backend.achievements.domain.model.commands;

public record DeleteAchievementCommand(Long achievementId, Long userId) {
    public DeleteAchievementCommand {
        if (achievementId == null) throw new IllegalArgumentException("achievementId cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
    }
}
