package com.rbservicios.demo_graphQL.adapter.graphql.context;

import java.util.Set;

public class UserContext {

    private final Long userId;
    private final String username;
    private final Set<String> roles;

    public UserContext(Long userId, String username, Set<String> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
