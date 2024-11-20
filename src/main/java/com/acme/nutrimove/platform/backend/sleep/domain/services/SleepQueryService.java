package com.acme.nutrimove.platform.backend.sleep.domain.services;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep;
import com.acme.nutrimove.platform.backend.sleep.domain.model.queries.GetSleepByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SleepQueryService {
    Optional<Sleep> handle(GetSleepByIdQuery query);
    List<Sleep> getAllSleepRecords();
}
