package com.acme.nutrimove.platform.backend.achievements.infrastructure.persistence.jpa;

import com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Optional<Achievement> findByIdAndUserId(Long achievementId, Long userId);

}
