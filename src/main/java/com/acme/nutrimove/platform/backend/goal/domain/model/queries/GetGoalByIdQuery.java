package com.acme.nutrimove.platform.backend.goal.domain.model.queries;

public record GetGoalByIdQuery(Long id) {

    public GetGoalByIdQuery {
        if (id == null) throw new NullPointerException("id is null");
    }
}
