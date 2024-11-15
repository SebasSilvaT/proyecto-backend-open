package com.acme.nutrimove.platform.backend.subscriptions.domain.services;

import com.acme.nutrimove.platform.backend.subscriptions.domain.model.aggregates.Subscription;
import com.acme.nutrimove.platform.backend.subscriptions.domain.model.commands.CreateSubscriptionCommand;

import java.util.Optional;


public interface SubscriptionsCommandService {

    Optional<Subscription> handle(CreateSubscriptionCommand command);

}
