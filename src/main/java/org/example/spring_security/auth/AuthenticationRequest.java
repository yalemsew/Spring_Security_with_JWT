package org.example.spring_security.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
