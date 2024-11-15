package com.acme.nutrimove.platform.backend.medicalhistory.domain.services;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.aggregates.MedicalHistory;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.CreateMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.UpdateMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.DeleteMedicalHistoryCommand;

import java.util.Optional;

public interface MedicalHistoryCommandService {
    Optional<MedicalHistory> handle(CreateMedicalHistoryCommand command);
    Optional<MedicalHistory> handle(UpdateMedicalHistoryCommand command);
    void handle(DeleteMedicalHistoryCommand command);
}
