package com.acme.nutrimove.platform.backend.food.domain.model.queries;

public record GetFoodByIdQuery(Long foodId) {
    public GetFoodByIdQuery {
        if (foodId == null) throw new IllegalArgumentException("Food ID cannot be null");
    }
}
