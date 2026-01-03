package com.rbservicios.demo_graphQL.infraestructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;

@Configuration
public class GraphQLDebugConfig {

    public DataFetcherExceptionResolver dataFetcherExceptionResolver() {
        return (exception, environment) -> {
            exception.printStackTrace();
            return null;
        };
    }
}
