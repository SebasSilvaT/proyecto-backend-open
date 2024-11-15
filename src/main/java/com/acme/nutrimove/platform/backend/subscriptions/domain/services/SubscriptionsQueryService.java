package com.acme.nutrimove.platform.backend.subscriptions.domain.services;

import com.acme.nutrimove.platform.backend.subscriptions.domain.model.aggregates.Subscription;
import com.acme.nutrimove.platform.backend.subscriptions.domain.model.queries.GetAllSubscriptionByDescriptionQuery;
import com.acme.nutrimove.platform.backend.subscriptions.domain.model.queries.GetSubscriptionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SubscriptionsQueryService {
    List<Subscription> handle(GetAllSubscriptionByDescriptionQuery query);
    List<Subscription> getAllActivities();

    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
}
