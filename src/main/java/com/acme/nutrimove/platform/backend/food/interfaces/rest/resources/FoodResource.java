package com.acme.nutrimove.platform.backend.food.interfaces.rest.resources;

public record FoodResource(
        Long id,
        String name,
        Integer calories,
        Integer proteins,
        Integer carbs,
        Integer fats,
        Long userId
) {}
