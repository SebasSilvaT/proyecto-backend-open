package com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.UpdateMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources.UpdateMedicalHistoryResource;

public class UpdateMedicalHistoryCommandFromResourceAssembler {

    public static UpdateMedicalHistoryCommand toCommand(Long id, UpdateMedicalHistoryResource resource) {
        return new UpdateMedicalHistoryCommand(
                id,
                resource.userId(),
                resource.date(),
                resource.condition(),
                resource.description()
        );
    }
}
