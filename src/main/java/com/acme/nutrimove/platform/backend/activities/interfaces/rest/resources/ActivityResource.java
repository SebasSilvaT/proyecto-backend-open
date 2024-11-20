package com.acme.nutrimove.platform.backend.activities.interfaces.rest.resources;

public record ActivityResource(Long id, String name, String description, Integer duration, Long userId ) {
}
