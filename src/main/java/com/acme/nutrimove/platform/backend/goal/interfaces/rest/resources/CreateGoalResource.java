package com.acme.nutrimove.platform.backend.goal.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateGoalResource(String goal_type, LocalDate start_date, LocalDate end_date, Long userId) {

    public CreateGoalResource {
        if (goal_type == null || goal_type.isBlank())
            throw new IllegalArgumentException("goal_type cannot be null or empty");
        if (start_date == null) throw new IllegalArgumentException("start_date cannot be null");
        if (end_date == null) throw new IllegalArgumentException("end_date cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
    }
}
