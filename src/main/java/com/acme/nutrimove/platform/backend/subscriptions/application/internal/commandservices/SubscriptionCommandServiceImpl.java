package com.acme.nutrimove.platform.backend.subscriptions.application.internal.commandservices;



import com.acme.nutrimove.platform.backend.subscriptions.domain.model.aggregates.Subscription;
import com.acme.nutrimove.platform.backend.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.acme.nutrimove.platform.backend.subscriptions.domain.services.SubscriptionsCommandService;
import com.acme.nutrimove.platform.backend.subscriptions.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionsCommandService {

    private final SubscriptionRepository subRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subRepository) {
        this.subRepository = subRepository;

    }

    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {

        var sub = new Subscription(command);
        var createsub = this.subRepository.save(sub);
        return Optional.of(createsub);
    }
}
