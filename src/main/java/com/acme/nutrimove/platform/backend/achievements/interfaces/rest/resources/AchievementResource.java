package com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources;

import java.time.LocalDate;

public record AchievementResource(
        Long id,
        Long userId,
        String achievement,
        LocalDate date
) {}