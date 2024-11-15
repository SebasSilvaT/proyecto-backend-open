package com.acme.nutrimove.platform.backend.user.domain.model.commands;

public record UpdateUserCommand(
        Long userId,
        String name,
        String lastname,
        String email,
        String password,
        String privacy
) {}
