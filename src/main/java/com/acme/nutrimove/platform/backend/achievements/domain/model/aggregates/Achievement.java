package com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates;

import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.CreateAchievementCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Configuration
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @Column(nullable = false)
    private String achievement;

    @Setter
    @Column(nullable = false)
    private LocalDate date;

    protected Achievement() {
    }

    public Achievement(CreateAchievementCommand command, User user) {
        this.user = user;
        this.achievement = command.achievement();
        this.date = command.date();
    }
}