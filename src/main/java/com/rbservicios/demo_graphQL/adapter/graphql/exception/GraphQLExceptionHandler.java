package com.rbservicios.demo_graphQL.adapter.graphql.exception;

import com.rbservicios.demo_graphQL.domain.exception.OrderNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQLExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleOrderNotFound(OrderNotFoundException ex,
                                            DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .extensions(java.util.Map.of(
                        "errorCode","ORDER_NOT_FOUND",
                        "orderId", ex.getMessage().contains("id") ?
                                extractOrderId(ex.getMessage()) : "unknown"))
                .build();
    }

    private String extractOrderId(String message) {
        return message.replaceAll("\\D+","");
    }
}
