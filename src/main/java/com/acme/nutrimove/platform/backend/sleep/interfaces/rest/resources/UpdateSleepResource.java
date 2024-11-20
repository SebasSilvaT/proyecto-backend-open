package com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdateSleepResource(
        LocalDateTime date,
        int hoursSlept,
        String quality
) {}
