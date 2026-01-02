package com.rbservicios.demo_graphQL.adapter.graphql.mutation;

import com.rbservicios.demo_graphQL.application.security.UserContext;
import com.rbservicios.demo_graphQL.adapter.graphql.mapper.OrderGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.OrderGql;
import com.rbservicios.demo_graphQL.adapter.graphql.resolver.BaseGraphQLResolver;
import com.rbservicios.demo_graphQL.application.command.port.CreateOrderCommand;
import graphql.GraphQLException;
import graphql.GraphqlErrorBuilder;
import org.springframework.graphql.execution.ErrorType;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderMutationResolver extends BaseGraphQLResolver {

    private final CreateOrderCommand command;

    public OrderMutationResolver(CreateOrderCommand command) {
        this.command = command;
    }

    @MutationMapping
    public OrderGql createOrder(
            @Argument List<Long> productIds,
            DataFetchingEnvironment env) throws Throwable {

        UserContext user = getUserContext(env);

        if (user == null) {
            throw new GraphQLException("Unauthorized");
        }

        if (!user.hasRole("USER")) {
            throw (Throwable) GraphqlErrorBuilder.newError()
                    .message("Forbidden")
                    .errorType(ErrorType.FORBIDDEN)
                    .build();
        }

        return OrderGqlMapper.fromDomain(command.execute(user.getUserId(), productIds));
    }

}
