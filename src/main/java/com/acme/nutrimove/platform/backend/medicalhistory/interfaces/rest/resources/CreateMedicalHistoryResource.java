package com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateMedicalHistoryResource(Long userId, LocalDate date, String condition, String description) {}
