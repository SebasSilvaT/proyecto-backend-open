package com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateAchievementResource(
        Long userId,
        String achievement,
        LocalDate date
) {}
