package com.acme.nutrimove.platform.backend.user.interfaces.rest.resources;

public record CreateUserResource(
        String name,
        String lastname,
        String email,
        String password,
        String privacy
) {
    public CreateUserResource {
        if (name == null) throw new NullPointerException("name cannot be null");
        if (lastname == null) throw new NullPointerException("lastname cannot be null");
        if (email == null) throw new NullPointerException("email cannot be null");
        if (password == null) throw new NullPointerException("password cannot be null");
        if (privacy == null) throw new NullPointerException("privacy cannot be null");
    }
}
