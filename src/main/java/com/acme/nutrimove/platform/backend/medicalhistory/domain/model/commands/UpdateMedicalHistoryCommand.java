package com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands;

import java.time.LocalDate;

public record UpdateMedicalHistoryCommand(Long id, Long userId, LocalDate date, String condition, String description) {}
