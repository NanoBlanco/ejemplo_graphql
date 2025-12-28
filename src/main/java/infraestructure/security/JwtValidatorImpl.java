package infraestructure.security;

import adapter.graphql.context.UserContext;
import application.security.JwtValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtValidatorImpl implements JwtValidator {

    private final JwtParser parser;

    public JwtValidatorImpl(
            @Value("${security.jwt.secret}") String secret) {
        this.parser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build();
    }

    @Override
    public UserContext validate(String token) {
        Claims claims = parser
                .parseClaimsJws(token)
                .getBody();

        Long userId = claims.get("userId", Long.class);
        String username = claims.getSubject();
        Set<String> roles = new HashSet<>(claims.get("roles", List.class));

        return new UserContext(userId, username, roles);
    }
}
