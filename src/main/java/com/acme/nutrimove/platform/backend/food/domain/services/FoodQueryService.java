package com.acme.nutrimove.platform.backend.food.domain.services;

import com.acme.nutrimove.platform.backend.food.domain.model.aggregates.Food;
import com.acme.nutrimove.platform.backend.food.domain.model.queries.GetFoodByIdQuery;
import com.acme.nutrimove.platform.backend.food.domain.model.queries.GetAllFoodsByUserQuery;

import java.util.List;
import java.util.Optional;

public interface FoodQueryService {
    Optional<Food> handle(GetFoodByIdQuery query);
    List<Food> handle(GetAllFoodsByUserQuery query);
    List<Food> getAllFoods();
}
