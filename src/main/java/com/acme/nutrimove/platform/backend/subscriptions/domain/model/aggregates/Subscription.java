package com.acme.nutrimove.platform.backend.subscriptions.domain.model.aggregates;

import com.acme.nutrimove.platform.backend.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Configuration
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column(nullable = false)
    @Getter
    private Double price;

    @Column(nullable = false)
    @Getter
    private Integer monthDuration;

    @Column(nullable = false)
    @Getter
    private Boolean trial;

    @Column(nullable = false)
    @Getter
    private Long userId;

    // Constructor vacío necesario para JPA
    protected Subscription() {
    }

    public Subscription(CreateSubscriptionCommand command) {
        this.description = command.description();
        this.price = command.price();
        this.monthDuration = command.monthDuration();
        this.trial = command.trial();
        this.userId = command.userId();
    }

}
