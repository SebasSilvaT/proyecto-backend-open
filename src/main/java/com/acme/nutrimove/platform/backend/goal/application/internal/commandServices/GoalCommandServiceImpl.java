package com.acme.nutrimove.platform.backend.goal.application.internal.commandServices;

import com.acme.nutrimove.platform.backend.goal.domain.model.aggregates.Goal;
import com.acme.nutrimove.platform.backend.goal.domain.model.commands.CreateGoalCommand;
import com.acme.nutrimove.platform.backend.goal.domain.model.commands.DeleteGoalCommand;
import com.acme.nutrimove.platform.backend.goal.domain.model.commands.UpdateGoalCommand;
import com.acme.nutrimove.platform.backend.goal.domain.services.GoalCommandService;
import com.acme.nutrimove.platform.backend.goal.infrastructure.persistance.jpa.GoalRepository;
import com.acme.nutrimove.platform.backend.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoalCommandServiceImpl implements GoalCommandService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalCommandServiceImpl(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<Goal> handle(CreateGoalCommand command) {

        var goal = new Goal(command);
        var createdGoal = this.goalRepository.save(goal);
        return Optional.of(createdGoal);
    }

    @Override
    public Optional<Goal> handle(UpdateGoalCommand command) {
        var goal = goalRepository.findById(command.id());
        if (goal.isPresent()) {
            goal.get().setGoal_type(command.goal_type());
            goal.get().setStart_date(command.start_date());
            goal.get().setEnd_date(command.end_date());
            var updatedGoal = this.goalRepository.save(goal.get());
            return Optional.of(updatedGoal);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void handle(DeleteGoalCommand command) {
        goalRepository.deleteById(command.goalId());

    }
}
