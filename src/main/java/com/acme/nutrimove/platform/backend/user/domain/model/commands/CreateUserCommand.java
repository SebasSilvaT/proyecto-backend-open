package com.acme.nutrimove.platform.backend.user.domain.model.commands;

import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User.Privacy;

public record CreateUserCommand(
        String name,
        String lastname,
        String email,
        String password,
        Privacy privacy
) {
    public CreateUserCommand {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (lastname == null || lastname.isBlank()) throw new IllegalArgumentException("Lastname cannot be null or empty");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or empty");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("Password cannot be null or empty");
        if (privacy == null) throw new IllegalArgumentException("Privacy cannot be null");
    }
}
