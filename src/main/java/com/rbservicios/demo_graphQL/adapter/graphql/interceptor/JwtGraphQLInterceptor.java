package com.rbservicios.demo_graphQL.adapter.graphql.interceptor;

import com.rbservicios.demo_graphQL.application.security.UserContext;
import com.rbservicios.demo_graphQL.application.security.JwtValidator;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class JwtGraphQLInterceptor implements WebGraphQlInterceptor {

    private final JwtValidator jwtValidator;

    public JwtGraphQLInterceptor(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(
            WebGraphQlRequest request,
            Chain chain) {

        Optional<UserContext> userContext = Optional.empty();

            String authHeader =
                request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                userContext = jwtValidator.validate(token);
            }

        Optional<UserContext> finalUserContext = userContext;

        request.configureExecutionInput((executionInput, builder) -> {
            builder.graphQLContext(ctx -> {
                        finalUserContext.ifPresent(uc -> ctx.put("userContext", uc)
                        );
                    }

            );
            return builder.build();
        });

        return chain.next(request);
    }
}
