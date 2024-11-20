package com.acme.nutrimove.platform.backend.goal.application.internal.queryServices;

import com.acme.nutrimove.platform.backend.goal.domain.model.aggregates.Goal;
import com.acme.nutrimove.platform.backend.goal.domain.model.queries.GetGoalByIdQuery;
import com.acme.nutrimove.platform.backend.goal.domain.services.GoalQueryService;
import com.acme.nutrimove.platform.backend.goal.infrastructure.persistance.jpa.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GoalQueryServiceImpl implements GoalQueryService {

    private final GoalRepository goalRepository;

    public GoalQueryServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    @Override
    public Optional<Goal> handle(GetGoalByIdQuery query) {
        return this.goalRepository.findById(query.id());
    }
}
