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

        String authHeader =
                request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        UserContext userContext = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            userContext = jwtValidator.validate(token);
        }

        return chain.next(
                request.configureExecutionInput((execInput, builder) ->
                        builder.graphQLContext(ctx ->
                                ctx.put("userContext", userContext)
                        ).build()
                )
        );
    }
}
