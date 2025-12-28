package adapter.graphql.interceptor;

import adapter.graphql.context.UserContext;
import application.security.JwtValidator;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

public class JwtGraphQLInterceptor implements WebGraphQlInterceptor {

    private final JwtValidator jwtValidator;

    public JwtGraphQLInterceptor(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(
            WebGraphQlRequest request,
            Chain chain) {

        UserContext userContext = null;

        try {
            String authHeader =
                request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                userContext = jwtValidator.validate(token);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final UserContext resolvedUserContext = userContext;

        request.configureExecutionInput((executionInput, builder) -> {
            builder.graphQLContext(ctx ->
                    ctx.put("userContext", resolvedUserContext)
            );
            return builder.build();
        });

        return chain.next(request);
    }
}
