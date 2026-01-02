package com.rbservicios.demo_graphQL.infraestructure.security;

import com.rbservicios.demo_graphQL.application.security.UserContext;
import com.rbservicios.demo_graphQL.application.security.JwtValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JwtValidatorImpl implements JwtValidator {

    private final JwtParser parser;

    public JwtValidatorImpl(
            @Value("${security.jwt.secret}") String secret) {

        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException(
                    "JWT secret must be at least 32 characters long"
            );
        }

        this.parser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build();
    }

    @Override
    public Optional<UserContext> validate(String token) {

        try {

            Claims claims = parser
                    .parseClaimsJws(token)
                    .getBody();

            Long userId = claims.get("userId", Long.class);
            String username = claims.getSubject();

            return Optional.of(new UserContext(userId, username));
        } catch (Exception e) {
            return Optional.empty();
        }


    }
}
