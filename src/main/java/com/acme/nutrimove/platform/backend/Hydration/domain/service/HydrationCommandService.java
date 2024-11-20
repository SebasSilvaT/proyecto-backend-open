package com.acme.nutrimove.platform.backend.Hydration.domain.service;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.aggregates.Hydration;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.CreateHydrationCommand;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.DeleteHydrationCommand;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.UpdateHydrationCommand;

import java.util.Optional;

public interface HydrationCommandService {

    Optional<Hydration> handle(CreateHydrationCommand command);

    Optional<Hydration> handle(UpdateHydrationCommand command);

    void handle(DeleteHydrationCommand command);
}
