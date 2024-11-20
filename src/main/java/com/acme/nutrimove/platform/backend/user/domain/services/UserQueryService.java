package com.acme.nutrimove.platform.backend.user.domain.services;

import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.model.queries.GetUserByIdQuery;
import com.acme.nutrimove.platform.backend.user.domain.model.queries.GetAllUsersByEmailQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    List<User> handle(GetAllUsersByEmailQuery query);
    List<User> getAllUsers();
    Optional<User> findById(Long id);
    Optional<User> handle(GetUserByIdQuery query);
}