package com.acme.nutrimove.platform.backend.activities.interfaces.rest.resources;

public record CreateActivityResource(
        String name,
        String description,
        Integer duration,
        Long userId
) {}
