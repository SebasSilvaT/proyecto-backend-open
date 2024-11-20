package com.acme.nutrimove.platform.backend.goal.domain.services;

import com.acme.nutrimove.platform.backend.goal.domain.model.aggregates.Goal;
import com.acme.nutrimove.platform.backend.goal.domain.model.queries.GetGoalByIdQuery;

import java.util.List;
import java.util.Optional;

public interface GoalQueryService {

    List<Goal> getAllGoals();

    Optional<Goal> handle(GetGoalByIdQuery query);
}
