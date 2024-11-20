package com.acme.nutrimove.platform.backend.food.interfaces.rest.resources;

public record CreateFoodResource(
        String name,
        Integer calories,
        Integer proteins,
        Integer carbs,
        Integer fats,
        Long userId
) {}
