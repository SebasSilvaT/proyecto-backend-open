package com.acme.nutrimove.platform.backend.Hydration.domain.service;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.aggregates.Hydration;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.queries.GetHydrationById;

import java.util.List;
import java.util.Optional;

public interface HydrationQueryService {

    List<Hydration> getAllHydration();

    Optional<Hydration> handle(GetHydrationById query);
}
