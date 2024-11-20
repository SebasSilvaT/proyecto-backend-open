package com.acme.nutrimove.platform.backend.goal.domain.model.commands;

import java.time.LocalDate;

public record UpdateGoalCommand(Long id, String goal_type, LocalDate start_date, LocalDate end_date) {

}
