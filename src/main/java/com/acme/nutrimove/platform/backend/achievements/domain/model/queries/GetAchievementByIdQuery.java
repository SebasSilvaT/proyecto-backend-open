package com.acme.nutrimove.platform.backend.achievements.domain.model.queries;

public record GetAchievementByIdQuery(
        Long achievementId,
        Long userId
) {}