package com.acme.nutrimove.platform.backend.Hydration.application.internal.queryServices;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.aggregates.Hydration;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.queries.GetHydrationById;
import com.acme.nutrimove.platform.backend.Hydration.domain.service.HydrationQueryService;
import com.acme.nutrimove.platform.backend.Hydration.infrastructure.persistance.jpa.HydrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HydrationQueryServiceImpl implements HydrationQueryService {

    private final HydrationRepository hydrationRepository;

    public HydrationQueryServiceImpl(HydrationRepository hydrationRepository) {
        this.hydrationRepository = hydrationRepository;
    }
    @Override
    public List<Hydration> getAllHydration() {
        return this.hydrationRepository.findAll();
    }

    @Override
    public Optional<Hydration> handle(GetHydrationById query) {
        return this.hydrationRepository.findById(query.id());
    }
}
