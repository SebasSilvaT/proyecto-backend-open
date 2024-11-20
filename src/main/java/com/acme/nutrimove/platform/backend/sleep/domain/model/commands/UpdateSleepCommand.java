package com.acme.nutrimove.platform.backend.sleep.domain.model.commands;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep.Quality;
import java.time.LocalDateTime;

public record UpdateSleepCommand(
        Long sleepId,
        LocalDateTime date,
        int hoursSlept,
        Quality quality
) {}
