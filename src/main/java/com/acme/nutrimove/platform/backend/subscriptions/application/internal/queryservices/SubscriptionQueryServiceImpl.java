package com.acme.nutrimove.platform.backend.subscriptions.application.internal.queryservices;

import com.acme.nutrimove.platform.backend.subscriptions.domain.model.aggregates.Subscription;
import com.acme.nutrimove.platform.backend.subscriptions.domain.model.queries.GetAllSubscriptionByDescriptionQuery;
import com.acme.nutrimove.platform.backend.subscriptions.domain.model.queries.GetSubscriptionByIdQuery;
import com.acme.nutrimove.platform.backend.subscriptions.domain.services.SubscriptionsQueryService;
import com.acme.nutrimove.platform.backend.subscriptions.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionsQueryService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository Repository) {
        this.subscriptionRepository = Repository;
    }

    @Override
    public List<Subscription> getAllActivities() {
        return subscriptionRepository.findAll();
    }

    @Override
    public List<Subscription> handle(GetAllSubscriptionByDescriptionQuery query) {
        return this.subscriptionRepository.findAllByDescription(query.description());
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return this.subscriptionRepository.findById(query.id());
    }
}
