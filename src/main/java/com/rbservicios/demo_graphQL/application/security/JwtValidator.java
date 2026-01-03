package com.rbservicios.demo_graphQL.application.security;

import java.util.Optional;

public interface JwtValidator {

    UserContext validate(String token);
}
