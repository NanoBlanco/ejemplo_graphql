package com.rbservicios.demo_graphQL.adapter.graphql.controller;

import com.rbservicios.demo_graphQL.application.command.port.DeleteOrderCommand;
import com.rbservicios.demo_graphQL.application.command.port.DeleteProductCommand;
import com.rbservicios.demo_graphQL.application.security.UserContext;
import com.rbservicios.demo_graphQL.adapter.graphql.mapper.OrderGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.OrderGql;
import com.rbservicios.demo_graphQL.application.command.port.CreateOrderCommand;
import graphql.GraphQLException;
import graphql.GraphqlErrorBuilder;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {

    private final CreateOrderCommand command;
    private final DeleteOrderCommand deleteCommand;

    public OrderController(CreateOrderCommand command, DeleteOrderCommand deleteCommand) {
        this.command = command;
        this.deleteCommand = deleteCommand;
    }

    @MutationMapping
    public OrderGql createOrder(
            @Argument List<Long> productIds,
            @ContextValue("userContext") UserContext userContext) throws Throwable {

        if (userContext == null) {
            throw new GraphQLException("Unauthorized");
        }

        if (!userContext.hasRole("USER")) {
            throw (Throwable) GraphqlErrorBuilder.newError()
                    .message("Forbidden")
                    .errorType(ErrorType.FORBIDDEN)
                    .build();
        }

        return OrderGqlMapper.fromDomain(command.execute(userContext.getUserId(), productIds));
    }

    @MutationMapping
    public boolean deleteOrder(
            @Argument Long orderId,
            @ContextValue(value = "userContext", required = false) UserContext userContext) {

        if (userContext == null || !userContext.hasRole("ADMIN")) {
            throw new GraphQLException("Unauthorized - Admin role required");
        }

        return deleteCommand.deleteOrder(orderId);
    }

}
