package com.rbservicios.demo_graphQL.adapter.graphql.interceptor;

import com.rbservicios.demo_graphQL.application.security.UserContext;
import com.rbservicios.demo_graphQL.application.security.JwtValidator;
import graphql.GraphQLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(JwtGraphQLInterceptor.class);

    public JwtGraphQLInterceptor(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(
            WebGraphQlRequest request,
            Chain chain) {

        log.info("Processing GraphQL request: {}", request.getDocument());

        UserContext userContext = null;

        String authHeader =
                request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            userContext = jwtValidator.validate(token);
            log.info("UserContext present: {}", userContext.getUserId());
        } else {
            log.warn("No Authorization header found or not Bearer token");
        }

        final UserContext finalUserContext = userContext;

        request.configureExecutionInput((executionInput, builder) ->
            /*
            // Crear un nuevo contexto o usar el existente
            GraphQLContext context = executionInput.getGraphQLContext();
            GraphQLContext.Builder contextBuilder = GraphQLContext.newContext();

            // Copiar valores existentes
            context.getKeys().forEach(key ->
                    contextBuilder.put(key, context.get(key))
            );

            // Agregar userContext, si existe
            finalUserContext.ifPresent(uc -> {
                contextBuilder.put("userContext", uc);
                log.info("userContext added to GraphQLContext");
            });


            return builder
                    .graphQLContext(contextBuilder.build())
                    .build();

             */
            builder
                    .graphQLContext(context -> {
                        if (finalUserContext != null) {
                            context.put("userContext", finalUserContext);
                        }
                    })
                    .build()
        );

        return chain.next(request);
    }
}
