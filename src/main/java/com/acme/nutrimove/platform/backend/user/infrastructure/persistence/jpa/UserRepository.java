package com.acme.nutrimove.platform.backend.user.infrastructure.persistence.jpa;

import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    List<User> findAllByEmail(String email);

    Optional<User> findById(Long id);
}
