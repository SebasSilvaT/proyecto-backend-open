package com.acme.nutrimove.platform.backend.sleep.domain.model.commands;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep.Quality;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

import java.time.LocalDateTime;

public record CreateSleepCommand(
        User user,
        LocalDateTime date,
        int hoursSlept,
        Quality quality
) {}