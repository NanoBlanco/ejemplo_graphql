package com.rbservicios.demo_graphQL.application.security;

import com.rbservicios.demo_graphQL.adapter.graphql.context.UserContext;

public interface JwtValidator {
    UserContext validate(String token);
}
