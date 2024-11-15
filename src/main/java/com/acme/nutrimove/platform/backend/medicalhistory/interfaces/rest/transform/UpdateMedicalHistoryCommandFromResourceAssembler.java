package com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.UpdateMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources.UpdateMedicalHistoryResource;
import org.springframework.stereotype.Component;

@Component
public class UpdateMedicalHistoryCommandFromResourceAssembler {

    public UpdateMedicalHistoryCommand toCommand(Long id, Long userId, UpdateMedicalHistoryResource resource) {
        return new UpdateMedicalHistoryCommand(
                id,
                userId,
                resource.date(),
                resource.medicalCondition(),
                resource.description()
        );
    }
}
