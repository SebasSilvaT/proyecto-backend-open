package com.acme.nutrimove.platform.backend.goal.domain.services;

import com.acme.nutrimove.platform.backend.goal.domain.model.aggregates.Goal;
import com.acme.nutrimove.platform.backend.goal.domain.model.commands.CreateGoalCommand;
import com.acme.nutrimove.platform.backend.goal.domain.model.commands.DeleteGoalCommand;
import com.acme.nutrimove.platform.backend.goal.domain.model.commands.UpdateGoalCommand;

import java.util.Optional;

public interface GoalCommandService {

    Optional<Goal> handle(CreateGoalCommand command);

    Optional<Goal> handle(UpdateGoalCommand command);

    void handle(DeleteGoalCommand command);

}
