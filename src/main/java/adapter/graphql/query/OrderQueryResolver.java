package adapter.graphql.query;

import adapter.graphql.context.UserContext;
import adapter.graphql.mapper.OrderGqlMapper;
import adapter.graphql.model.OrderGql;
import adapter.graphql.resolver.BaseGraphQLResolver;
import application.query.port.GetOrdersByUserQuery;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderQueryResolver extends BaseGraphQLResolver {

    private final GetOrdersByUserQuery query;

    public OrderQueryResolver(GetOrdersByUserQuery query) {
        this.query = query;
    }

    /*
    Por temas de no violar informacion de usuarios debe ser restringida
    y solo utilizar el metodo de abajo

    @QueryMapping
    public List<OrderGql> ordersByUser(@Argument Long userId) {
        return query.execute(userId)
                .stream()
                .map(OrderGqlMapper::fromDomain)
                .toList();
    }
    */
    @QueryMapping
    public List<OrderGql> myOrders(
            @Argument Long userId,
            DataFetchingEnvironment env) {

        UserContext user = getUserContext(env);

        if (user == null || !user.hasRole("ADMIN")) {
            throw new GraphQLException("Unauthorized");
        }

        return query.execute(userId)
                .stream()
                .map(OrderGqlMapper::fromDomain)
                .toList();
    }
}
