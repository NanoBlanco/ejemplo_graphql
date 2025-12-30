package com.rbservicios.demo_graphQL.application.query.port;

import com.rbservicios.demo_graphQL.domain.model.Order;

import java.util.List;

public interface GetOrdersByUserQuery {

    List<Order> execute(Long userId);
}
