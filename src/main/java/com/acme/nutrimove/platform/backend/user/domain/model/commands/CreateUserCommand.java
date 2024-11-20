package com.acme.nutrimove.platform.backend.user.domain.model.commands;


import com.acme.nutrimove.platform.backend.user.domain.ValueObjects.Privacy;

public record CreateUserCommand(
        String name,
        String lastname,
        String email,
        String password,
        Privacy privacy
) {}
