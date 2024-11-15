package com.acme.nutrimove.platform.backend.medicalhistory.application.internal.commandservices;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.aggregates.MedicalHistory;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.CreateMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.UpdateMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.DeleteMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.services.MedicalHistoryCommandService;
import com.acme.nutrimove.platform.backend.medicalhistory.infrastructure.persistence.jpa.MedicalHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalHistoryCommandServiceImpl implements MedicalHistoryCommandService {

    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryCommandServiceImpl(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @Override
    public Optional<MedicalHistory> handle(CreateMedicalHistoryCommand command) {
        var medicalHistory = new MedicalHistory(command.userId(), command.date(), command.condition(), command.description());
        return Optional.of(medicalHistoryRepository.save(medicalHistory));
    }

    @Override
    public Optional<MedicalHistory> handle(UpdateMedicalHistoryCommand command) {
        return medicalHistoryRepository.findByIdAndUserId(command.id(), command.userId()).map(medicalHistory -> {
            medicalHistory.setDate(command.date());
            medicalHistory.setMedicalCondition(command.condition());
            medicalHistory.setDescription(command.description());
            return medicalHistoryRepository.save(medicalHistory);
        });
    }

    @Override
    public void handle(DeleteMedicalHistoryCommand command) {
        medicalHistoryRepository.deleteByIdAndUserId(command.id(), command.userId());
    }
}
