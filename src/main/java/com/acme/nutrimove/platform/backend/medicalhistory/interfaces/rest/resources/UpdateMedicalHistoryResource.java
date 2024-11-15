package com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateMedicalHistoryResource(
        LocalDate date,
        String medicalCondition,
        String description
) {}