package com.acme.nutrimove.platform.backend.sleep.application.internal.queryservices;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep;
import com.acme.nutrimove.platform.backend.sleep.domain.model.queries.GetSleepByIdQuery;
import com.acme.nutrimove.platform.backend.sleep.domain.services.SleepQueryService;
import com.acme.nutrimove.platform.backend.sleep.infrastructure.persistence.jpa.SleepRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SleepQueryServiceImpl implements SleepQueryService {

    private final SleepRepository sleepRepository;

    public SleepQueryServiceImpl(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    @Override
    public Optional<Sleep> handle(GetSleepByIdQuery query) {
        return sleepRepository.findById(query.id());
    }

    @Override
    public List<Sleep> getAllSleepRecords() {
        return sleepRepository.findAll();
    }
}
