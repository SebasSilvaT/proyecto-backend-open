package com.acme.nutrimove.platform.backend.Hydration.application.internal.commnandServices;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.aggregates.Hydration;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.CreateHydrationCommand;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.DeleteHydrationCommand;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.UpdateHydrationCommand;
import com.acme.nutrimove.platform.backend.Hydration.domain.service.HydrationCommandService;
import com.acme.nutrimove.platform.backend.Hydration.infrastructure.persistance.jpa.HydrationRepository;
import com.acme.nutrimove.platform.backend.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class hydrationCommandServiceImpl implements HydrationCommandService {

    private final HydrationRepository hydrationRepository;
    private final UserRepository userRepository;

    public hydrationCommandServiceImpl(HydrationRepository hydrationRepository, UserRepository userRepository) {
        this.hydrationRepository = hydrationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Hydration> handle(CreateHydrationCommand command) {

        var user = userRepository.findById(command.userId().getId());
        if(user.isEmpty()){
            return Optional.empty();
        }

        var hydration = new Hydration(command);
        var createHydration = this.hydrationRepository.save(hydration);
        return Optional.of(createHydration);
    }

    @Override
    public Optional<Hydration> handle(UpdateHydrationCommand command) {
        var hydration =hydrationRepository.findById(command.id());
        if(hydration.isPresent()){
            hydration.get().setDate(command.date());
            hydration.get().setQuantity_ml(command.quantity_ml());
            var updateHydration = this.hydrationRepository.save(hydration.get());
            return Optional.of(updateHydration);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public void handle(DeleteHydrationCommand command) {
        this.hydrationRepository.deleteById(command.id());
    }
}
