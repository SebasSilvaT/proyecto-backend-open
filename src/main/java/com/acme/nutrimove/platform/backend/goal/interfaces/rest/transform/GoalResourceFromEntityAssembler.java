package com.acme.nutrimove.platform.backend.goal.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.goal.domain.model.aggregates.Goal;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.resources.GoalResource;

public class GoalResourceFromEntityAssembler {

    public static GoalResource toResourceFromEntity(Goal goal) {
        return new GoalResource(goal.getId(), goal.getGoal_type(),
                goal.getStart_date(), goal.getEnd_date(),
                goal.getUserId() != null ? goal.getUserId().getId() : null);
    }
}
