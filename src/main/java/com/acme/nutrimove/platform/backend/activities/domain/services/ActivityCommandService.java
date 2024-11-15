package com.acme.nutrimove.platform.backend.activities.domain.services;

import com.acme.nutrimove.platform.backend.activities.domain.model.aggregates.Activity;
import com.acme.nutrimove.platform.backend.activities.domain.model.commands.CreateActivityCommand;
import com.acme.nutrimove.platform.backend.activities.domain.model.commands.DeleteActivityCommand;

import java.util.Optional;

public interface ActivityCommandService {

    Optional<Activity> handle(CreateActivityCommand command);
    void handle(DeleteActivityCommand command);

}
