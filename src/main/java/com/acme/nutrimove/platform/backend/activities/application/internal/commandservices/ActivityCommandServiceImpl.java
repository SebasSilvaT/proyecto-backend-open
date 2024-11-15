package com.acme.nutrimove.platform.backend.activities.application.internal.commandservices;

import com.acme.nutrimove.platform.backend.activities.domain.model.aggregates.Activity;
import com.acme.nutrimove.platform.backend.activities.domain.model.commands.CreateActivityCommand;
import com.acme.nutrimove.platform.backend.activities.domain.model.commands.DeleteActivityCommand;
import com.acme.nutrimove.platform.backend.activities.domain.services.ActivityCommandService;
import com.acme.nutrimove.platform.backend.activities.infrastructure.persistence.jpa.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityCommandServiceImpl implements ActivityCommandService {

    private final ActivityRepository activityRepository;

    public ActivityCommandServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;

    }


    @Override
    public Optional<Activity> handle(CreateActivityCommand command) {
        if (activityRepository.existsByNameAndDescription(command.name(), command.description())) {
            throw new IllegalArgumentException("Activity with same name already exists for this description");
        }
        var activity = new Activity(command);
        var createActivity = this.activityRepository.save(activity);
        return Optional.of(createActivity);
    }
    @Override
    public void handle(DeleteActivityCommand command) {
        activityRepository.deleteById(command.activityId());
    }


}
