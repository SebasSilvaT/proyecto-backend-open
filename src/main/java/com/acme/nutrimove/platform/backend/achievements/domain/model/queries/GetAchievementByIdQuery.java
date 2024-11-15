package com.acme.nutrimove.platform.backend.achievements.domain.model.queries;

public record GetAchievementByIdQuery(Long achievementId, Long userId) {
    public GetAchievementByIdQuery {
        if (achievementId == null) throw new IllegalArgumentException("achievementId cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
    }
}
