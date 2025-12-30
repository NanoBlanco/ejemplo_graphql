package com.rbservicios.demo_graphQL.adapter.graphql.mapper;

import com.rbservicios.demo_graphQL.adapter.graphql.model.OrderGql;
import com.rbservicios.demo_graphQL.domain.model.Order;

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
