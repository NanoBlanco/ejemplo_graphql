package com.rbservicios.demo_graphQL.application.security;

import java.util.Set;

public class UserContext {

    private final Long userId;
    private final String email;

    public UserContext(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public boolean hasRole(String role) {
        return "USER".equals(role);
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
