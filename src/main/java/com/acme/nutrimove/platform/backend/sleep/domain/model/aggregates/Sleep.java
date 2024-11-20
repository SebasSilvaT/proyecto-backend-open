package com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates;

import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.CreateSleepCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Sleep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY) // Relación con User
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @Column(nullable = false)
    private LocalDateTime date;

    @Setter
    @Column(nullable = false)
    private int hoursSlept;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Quality quality;

    public enum Quality {
        GOOD, BAD
    }

    public Sleep(CreateSleepCommand command) {
        this.user = command.user();  // Establece el User desde el comando
        this.date = command.date();  // Establece la fecha desde el comando
        this.hoursSlept = command.hoursSlept();  // Establece las horas dormidas desde el comando
        this.quality = command.quality();  // Establece la calidad del sueño desde el comando
    }

    public Sleep() {}
}
