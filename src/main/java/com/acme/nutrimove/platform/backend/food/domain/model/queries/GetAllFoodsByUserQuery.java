package com.acme.nutrimove.platform.backend.food.domain.model.queries;

import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;

public record GetAllFoodsByUserQuery(User user) {
    public GetAllFoodsByUserQuery {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
    }
}
