package com.rbservicios.demo_graphQL.adapter.graphql.resolver;

import com.rbservicios.demo_graphQL.application.security.UserContext;
import com.rbservicios.demo_graphQL.adapter.graphql.mapper.OrderGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.OrderGql;
import com.rbservicios.demo_graphQL.application.query.port.GetOrdersByUserQuery;
import graphql.GraphQLContext;
import graphql.GraphQLException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderQueryResolver {

    private final GetOrdersByUserQuery query;

    public OrderQueryResolver(GetOrdersByUserQuery query) {
        this.query = query;
    }

    @QueryMapping
    public List<OrderGql> ordersByUser(
            @Argument Long userId,
            GraphQLContext context) {

        UserContext userContext = context.get("userContext");

        if (userContext == null || !userContext.hasRole("USER")) {
            throw new GraphQLException("Unauthorized");
        }

        return query.execute(userId)
                .stream()
                .map(OrderGqlMapper::fromDomain)
                .toList();
    }
}
