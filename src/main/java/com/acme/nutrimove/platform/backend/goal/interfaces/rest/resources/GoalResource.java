package com.acme.nutrimove.platform.backend.goal.interfaces.rest.resources;

import java.time.LocalDate;

public record GoalResource(Long id, String goal_type, LocalDate start_date, LocalDate end_date, Long userId) {
}
