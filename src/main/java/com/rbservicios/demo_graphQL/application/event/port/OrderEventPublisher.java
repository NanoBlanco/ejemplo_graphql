package com.rbservicios.demo_graphQL.application.event.port;

import com.rbservicios.demo_graphQL.domain.model.Order;

public interface OrderEventPublisher {
    void orderCreated(Order order);
}
