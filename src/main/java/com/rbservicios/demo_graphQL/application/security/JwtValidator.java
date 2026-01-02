package com.rbservicios.demo_graphQL.application.security;

import java.util.Optional;

public interface JwtValidator {

    Optional<UserContext> validate(String token);
}
