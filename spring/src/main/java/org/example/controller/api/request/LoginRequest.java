package org.example.controller.api.request;

public record LoginRequest(
    String email,
    String password
) {
}
