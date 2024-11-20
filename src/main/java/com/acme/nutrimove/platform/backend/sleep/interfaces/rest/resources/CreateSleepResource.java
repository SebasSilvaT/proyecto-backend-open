package com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateSleepResource(
        Long userId,
        LocalDateTime date,
        int hoursSlept,
        String quality
) {
    public CreateSleepResource {
        if (userId == null) throw new NullPointerException("userId cannot be null");
        if (date == null) throw new NullPointerException("date cannot be null");
        if (quality == null) throw new NullPointerException("quality cannot be null");
    }
}
