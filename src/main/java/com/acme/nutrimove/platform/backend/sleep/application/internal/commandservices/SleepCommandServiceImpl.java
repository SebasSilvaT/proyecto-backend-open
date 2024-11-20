package com.acme.nutrimove.platform.backend.sleep.application.internal.commandservices;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep;
import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.CreateSleepCommand;
import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.DeleteSleepCommand;
import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.UpdateSleepCommand;
import com.acme.nutrimove.platform.backend.sleep.domain.services.SleepCommandService;
import com.acme.nutrimove.platform.backend.sleep.infrastructure.persistence.jpa.SleepRepository;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SleepCommandServiceImpl implements SleepCommandService {

    private final SleepRepository sleepRepository;
    private final UserQueryService userQueryService; // Servicio para obtener el usuario

    public SleepCommandServiceImpl(SleepRepository sleepRepository, UserQueryService userQueryService) {
        this.sleepRepository = sleepRepository;
        this.userQueryService = userQueryService;
    }

    @Override
    public Optional<Sleep> handle(CreateSleepCommand command) {
        var sleep = new Sleep(command);  // El constructor de Sleep acepta el comando directamente
        var createdSleep = sleepRepository.save(sleep);
        return Optional.of(createdSleep);
    }

    @Override
    public void handle(DeleteSleepCommand command) {
        sleepRepository.deleteById(command.sleepId());
    }

    @Override
    public Optional<Sleep> handle(UpdateSleepCommand command) {
        return sleepRepository.findById(command.sleepId()).map(sleep -> {
            sleep.setHoursSlept(command.hoursSlept());
            sleep.setQuality(command.quality());
            sleep.setDate(command.date());
            return sleepRepository.save(sleep);
        });
    }
}
