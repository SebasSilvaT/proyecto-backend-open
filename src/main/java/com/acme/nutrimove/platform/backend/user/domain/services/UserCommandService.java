package com.acme.nutrimove.platform.backend.user.domain.services;

import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.model.commands.CreateUserCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.commands.DeleteUserCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.commands.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    void handle(DeleteUserCommand command);
    Optional<User> handle(UpdateUserCommand command); // Nuevo método para actualizar el usuario
}