package com.acme.nutrimove.platform.backend.sleep.infrastructure.persistence.jpa;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {

    List<Sleep> findAllByUserId(Long userId);

    List<Sleep> findByUserId(Long userId);

    Optional<Sleep> findByUserIdAndDate(Long userId, LocalDateTime date);
}
