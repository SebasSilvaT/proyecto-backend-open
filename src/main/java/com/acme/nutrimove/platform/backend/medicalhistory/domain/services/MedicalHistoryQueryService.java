package com.acme.nutrimove.platform.backend.medicalhistory.domain.services;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.aggregates.MedicalHistory;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.queries.GetMedicalHistoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryQueryService {
    List<MedicalHistory> getAllMedicalHistories();
    Optional<MedicalHistory> handle(GetMedicalHistoryByIdQuery query);
}
