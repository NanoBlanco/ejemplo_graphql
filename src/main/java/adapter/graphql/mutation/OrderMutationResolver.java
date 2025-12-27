package adapter.graphql.mutation;

import adapter.graphql.mapper.OrderGqlMapper;
import adapter.graphql.model.OrderGql;
import application.command.port.CreateOrderCommand;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMutationResolver {

    private final CreateOrderCommand command;

    public OrderMutationResolver(CreateOrderCommand command) {
        this.command = command;
    }

    @MutationMapping
    public OrderGql createOrder(
            @Argument Long userId,
            @Argument List<Long> productIds) {
        return OrderGqlMapper.fromDomain(command.execute(userId, productIds));
    }

}
