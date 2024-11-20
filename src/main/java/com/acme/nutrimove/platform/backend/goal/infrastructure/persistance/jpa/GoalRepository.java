package com.acme.nutrimove.platform.backend.goal.infrastructure.persistance.jpa;

import com.acme.nutrimove.platform.backend.goal.domain.model.aggregates.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
