package com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.aggregates.MedicalHistory;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources.MedicalHistoryResource;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryResourceFromEntityAssembler {

    public MedicalHistoryResource toResourceFromEntity(MedicalHistory medicalHistory) {
        return new MedicalHistoryResource(
                medicalHistory.getId(),
                medicalHistory.getUserId(),
                medicalHistory.getDate(),
                medicalHistory.getMedicalCondition(),
                medicalHistory.getDescription()
        );
    }
}
