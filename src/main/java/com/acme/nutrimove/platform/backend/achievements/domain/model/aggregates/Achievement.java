package com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates;

import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.CreateAchievementCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Configuration
@Getter // Genera getters para todos los campos
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;  // Foreign Key to User

    @Setter // Genera setter para campo mutable
    @Column(nullable = false)
    private String achievement;

    @Setter // Genera setter para campo mutable
    @Column(nullable = false)
    private LocalDate date;

    // Constructor vacío necesario para JPA
    protected Achievement() {
    }

    // Constructor usando comando para inicialización
    public Achievement(CreateAchievementCommand command) {
        this.userId = command.userId();
        this.achievement = command.achievement();
        this.date = command.date();
    }
}
