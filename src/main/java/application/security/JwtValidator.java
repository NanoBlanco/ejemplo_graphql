package application.security;

import adapter.graphql.context.UserContext;

public interface JwtValidator {
    UserContext validate(String token);
}
