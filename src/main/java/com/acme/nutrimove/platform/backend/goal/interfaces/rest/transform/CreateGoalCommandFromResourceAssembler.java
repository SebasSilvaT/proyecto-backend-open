package com.acme.nutrimove.platform.backend.goal.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.goal.domain.model.commands.CreateGoalCommand;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.resources.CreateGoalResource;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

public class CreateGoalCommandFromResourceAssembler {

    public static CreateGoalCommand toCommand(CreateGoalResource resource, User userId) {
        return new CreateGoalCommand(resource.goal_type(), resource.start_date(), resource.end_date(), userId);
    }
}
