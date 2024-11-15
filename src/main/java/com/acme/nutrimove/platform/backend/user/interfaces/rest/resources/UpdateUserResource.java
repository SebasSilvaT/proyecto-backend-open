package com.acme.nutrimove.platform.backend.user.interfaces.rest.resources;

public record UpdateUserResource(
        String name,
        String lastname,
        String email,
        String password,
        String privacy
) {}
