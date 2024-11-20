package com.acme.nutrimove.platform.backend.activities.domain.model.aggregates;

import com.acme.nutrimove.platform.backend.activities.domain.model.commands.CreateActivityCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Configuration
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private String description;

    @Setter
    @Column(nullable = false)
    private Integer duration;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Activity(CreateActivityCommand command, User user) {
        this.name = command.name();
        this.description = command.description();
        this.duration = command.duration();
        this.user = user;
    }

    // Constructor vacío requerido por JPA
    public Activity() {}
}
