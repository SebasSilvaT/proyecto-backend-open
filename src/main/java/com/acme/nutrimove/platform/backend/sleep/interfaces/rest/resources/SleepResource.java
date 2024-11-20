package com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources;

import java.time.LocalDateTime;

public record SleepResource(
        Long id,
        Long userId,
        LocalDateTime date,
        int hoursSlept,
        String quality
) {
}
