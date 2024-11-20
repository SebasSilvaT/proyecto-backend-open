package com.acme.nutrimove.platform.backend.user.application.internal.commandservices;

import com.acme.nutrimove.platform.backend.user.domain.ValueObjects.Privacy;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.model.commands.CreateUserCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.commands.DeleteUserCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.commands.UpdateUserCommand;
import com.acme.nutrimove.platform.backend.user.domain.services.UserCommandService;
import com.acme.nutrimove.platform.backend.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        // Validación para evitar duplicidad de usuarios por email
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with the same email already exists");
        }

        // Crear y guardar el nuevo usuario
        var user = new User(command);
        var createdUser = this.userRepository.save(user);
        return Optional.of(createdUser);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        // Eliminar usuario por ID
        userRepository.deleteById(command.userId());
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        return userRepository.findById(command.userId()).map(user -> {
            user.setName(command.name());
            user.setLastname(command.lastname());
            user.setEmail(command.email());
            user.setPassword(command.password());
            user.setPrivacy(Privacy.valueOf(command.privacy().toUpperCase()));
            return userRepository.save(user);
        });
    }

}
