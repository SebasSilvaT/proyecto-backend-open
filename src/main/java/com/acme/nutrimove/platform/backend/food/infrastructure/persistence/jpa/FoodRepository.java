package com.acme.nutrimove.platform.backend.food.infrastructure.persistence.jpa;

import com.acme.nutrimove.platform.backend.food.domain.model.aggregates.Food;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByUser(User user);

    boolean existsByNameAndUser(String name, User user);
}
