package org.example.spring_security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.spring_security.user.Role;

public record RegisterRequest(
        String firstName,
        String lastName,
        String password,
        String username,
        Role role
) {
}
