package adapter.graphql.mapper;

import adapter.graphql.model.OrderGql;
import domain.model.Order;

public class OrderGqlMapper {

    public static OrderGql fromDomain(Order order) {
        return new OrderGql(
                order.getId(),
                order.getCreatedAt().toString(),
                order.getUserId(),
                order.getProductIds()
        );
    }
}
