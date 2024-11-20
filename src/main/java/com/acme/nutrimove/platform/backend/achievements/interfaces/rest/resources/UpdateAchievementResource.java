package com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateAchievementResource(
        Long achievementId,
        Long userId,
        String achievement,
        LocalDate date
) {}
