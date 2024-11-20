package com.acme.nutrimove.platform.backend.goal.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.goal.domain.model.commands.UpdateGoalCommand;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.resources.UpdateGoalResource;

public class UpdateGoalCommandFromResourceAssembler {

    public static UpdateGoalCommand toCommand(Long id, UpdateGoalResource resource) {
        return new UpdateGoalCommand(id, resource.goal_type(), resource.start_date(), resource.end_date());
    }
}
