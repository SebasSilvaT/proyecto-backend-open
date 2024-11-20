package com.acme.nutrimove.platform.backend.activities.application.internal.commandservices;

import com.acme.nutrimove.platform.backend.activities.domain.model.aggregates.Activity;
import com.acme.nutrimove.platform.backend.activities.domain.model.commands.CreateActivityCommand;
import com.acme.nutrimove.platform.backend.activities.domain.model.commands.DeleteActivityCommand;
import com.acme.nutrimove.platform.backend.activities.domain.services.ActivityCommandService;
import com.acme.nutrimove.platform.backend.activities.infrastructure.persistence.jpa.ActivityRepository;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityCommandServiceImpl implements ActivityCommandService {

    private final ActivityRepository activityRepository;
    private final UserQueryService userQueryService;

    public ActivityCommandServiceImpl(ActivityRepository activityRepository, UserQueryService userQueryService) {
        this.activityRepository = activityRepository;
        this.userQueryService = userQueryService;
    }

    @Override
    public Optional<Activity> handle(CreateActivityCommand command) {
        Optional<User> userOptional = userQueryService.findById(command.userId());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + command.userId());
        }
        User user = userOptional.get();

        if (activityRepository.existsByNameAndDescription(command.name(), command.description())) {
            throw new IllegalArgumentException("Activity with same name and description already exists");
        }

        var activity = new Activity(command, user);
        var createActivity = this.activityRepository.save(activity);
        return Optional.of(createActivity);
    }

    @Override
    public void handle(DeleteActivityCommand command) {
        activityRepository.deleteById(command.activityId());
    }
}
