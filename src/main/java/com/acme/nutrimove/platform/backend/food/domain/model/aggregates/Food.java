package com.acme.nutrimove.platform.backend.food.domain.model.aggregates;

import com.acme.nutrimove.platform.backend.food.domain.model.commands.CreateFoodCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private int calories;

    @Setter
    @Column(nullable = false)
    private int proteins;

    @Setter
    @Column(nullable = false)
    private int carbs;

    @Setter
    @Column(nullable = false)
    private int fats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Food() {
    }

    public Food(User user, String name, int calories, int proteins, int carbs, int fats) {
        this.user = user;
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

}
