package com.acme.nutrimove.platform.backend.user.application.internal.queryservices;

import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.model.queries.GetUserByIdQuery;
import com.acme.nutrimove.platform.backend.user.domain.model.queries.GetAllUsersByEmailQuery;
import com.acme.nutrimove.platform.backend.user.domain.services.UserQueryService;
import com.acme.nutrimove.platform.backend.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> handle(GetAllUsersByEmailQuery query) {
        return userRepository.findAllByEmail(query.email());
    }

    @Override
    public Optional<User> findById(Long userId) { return userRepository.findById(userId); }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }

}