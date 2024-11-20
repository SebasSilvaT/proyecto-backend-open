package com.acme.nutrimove.platform.backend.achievements.domain.model.commands;

import java.time.LocalDate;

public record CreateAchievementCommand(
        Long userId,
        String achievement,
        LocalDate date
) {}