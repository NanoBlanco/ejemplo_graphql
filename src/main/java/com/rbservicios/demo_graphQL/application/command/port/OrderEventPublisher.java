package com.rbservicios.demo_graphQL.application.command.port;

import com.rbservicios.demo_graphQL.application.event.OrderCreatedEvent;

public interface OrderEventPublisher {
    void publish(OrderCreatedEvent event);
}
