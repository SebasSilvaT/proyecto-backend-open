package com.acme.nutrimove.platform.backend.Hydration.infrastructure.persistance.jpa;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.aggregates.Hydration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HydrationRepository extends JpaRepository<Hydration, Long> {
}
