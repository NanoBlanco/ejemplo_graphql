package adapter.graphql.query;

import adapter.graphql.mapper.OrderGqlMapper;
import adapter.graphql.model.OrderGql;
import application.query.port.GetOrdersByUserQuery;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderQueryResolver {

    private final GetOrdersByUserQuery query;

    public OrderQueryResolver(GetOrdersByUserQuery query) {
        this.query = query;
    }

    @QueryMapping
    public List<OrderGql> ordersByUser(@Argument Long userId) {
        return query.execute(userId)
                .stream()
                .map(OrderGqlMapper::fromDomain)
                .toList();
    }
}
