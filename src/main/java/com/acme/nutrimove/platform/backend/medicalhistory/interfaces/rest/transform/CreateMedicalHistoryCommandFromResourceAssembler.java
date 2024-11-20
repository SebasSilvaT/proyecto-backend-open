package com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.CreateMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources.CreateMedicalHistoryResource;

public class CreateMedicalHistoryCommandFromResourceAssembler {

    public static CreateMedicalHistoryCommand toCommand(CreateMedicalHistoryResource resource) {
        return new CreateMedicalHistoryCommand(
                resource.userId(),
                resource.date(),
                resource.condition(),
                resource.description()
        );
    }
}
