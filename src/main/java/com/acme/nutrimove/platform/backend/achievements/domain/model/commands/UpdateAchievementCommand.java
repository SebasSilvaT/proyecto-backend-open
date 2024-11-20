package com.acme.nutrimove.platform.backend.achievements.domain.model.commands;

import java.time.LocalDate;

public record UpdateAchievementCommand(Long achievementId, Long userId, String achievement, LocalDate date) {}
