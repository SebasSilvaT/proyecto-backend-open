package com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.transform;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.CreateMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources.CreateMedicalHistoryResource;
import org.springframework.stereotype.Component;

@Component
public class CreateMedicalHistoryCommandFromResourceAssembler {

    public CreateMedicalHistoryCommand toCommand(CreateMedicalHistoryResource resource) {
        return new CreateMedicalHistoryCommand(
                resource.userId(),           // Sin "get"
                resource.date(),             // Sin "get"
                resource.medicalCondition(),  // Sin "get"
                resource.description()       // Sin "get"
        );
    }
}
