package com.acme.nutrimove.platform.backend.sleep.domain.services;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep;
import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.CreateSleepCommand;
import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.DeleteSleepCommand;
import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.UpdateSleepCommand;

import java.util.Optional;

public interface SleepCommandService {
    Optional<Sleep> handle(CreateSleepCommand command);
    void handle(DeleteSleepCommand command);
    Optional<Sleep> handle(UpdateSleepCommand command);
}
