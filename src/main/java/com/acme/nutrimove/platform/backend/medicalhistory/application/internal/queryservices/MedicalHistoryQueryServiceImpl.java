package com.acme.nutrimove.platform.backend.medicalhistory.application.internal.queryservices;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.aggregates.MedicalHistory;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.queries.GetMedicalHistoryByIdQuery;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.services.MedicalHistoryQueryService;
import com.acme.nutrimove.platform.backend.medicalhistory.infrastructure.persistence.jpa.MedicalHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryQueryServiceImpl implements MedicalHistoryQueryService {

    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryQueryServiceImpl(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @Override
    public List<MedicalHistory> getAllMedicalHistories() {
        return medicalHistoryRepository.findAll();
    }

    @Override
    public Optional<MedicalHistory> handle(GetMedicalHistoryByIdQuery query) {
        return medicalHistoryRepository.findByIdAndUserId(query.id(), query.userId());
    }
}
