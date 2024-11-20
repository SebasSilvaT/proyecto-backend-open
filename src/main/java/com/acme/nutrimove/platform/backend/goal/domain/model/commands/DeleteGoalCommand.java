package com.acme.nutrimove.platform.backend.goal.domain.model.commands;

public record DeleteGoalCommand(Long goalId) {
    public DeleteGoalCommand {
        if (goalId == null) throw new IllegalArgumentException("goalId is null");
    }
}
