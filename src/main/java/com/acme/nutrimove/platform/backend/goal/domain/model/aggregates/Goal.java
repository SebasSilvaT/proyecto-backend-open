package com.acme.nutrimove.platform.backend.goal.domain.model.aggregates;


import com.acme.nutrimove.platform.backend.goal.domain.model.commands.CreateGoalCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String goal_type;


    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDate start_date;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDate end_date;


    @Setter
    @ManyToOne(fetch = FetchType.LAZY) // Relación con User
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    //JPA
    public Goal() {
    }

    public Goal(CreateGoalCommand command) {
        this.goal_type = command.goal_type();
        this.start_date = command.start_date();
        this.end_date = command.end_date();
        this.userId = command.userId();
    }
}