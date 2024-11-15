package com.acme.nutrimove.platform.backend.user.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UserResource(
        Long id,
        String name,
        String lastname,
        String email,
        LocalDateTime createdAt,
        String privacy
) {
}
