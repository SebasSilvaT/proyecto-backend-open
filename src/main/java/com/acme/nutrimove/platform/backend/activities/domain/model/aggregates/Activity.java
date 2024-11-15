package com.acme.nutrimove.platform.backend.activities.domain.model.aggregates;

import com.acme.nutrimove.platform.backend.activities.domain.model.commands.CreateActivityCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Configuration
@Getter // Genera getters para todos los campos
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Long userId;

    // Constructor que usa el comando para inicializar los campos
    public Activity(CreateActivityCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.duration = command.duration();
        this.userId = command.user_id();
    }

    // Constructor vacío requerido por JPA
    protected Activity() {}
}
