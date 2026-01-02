package com.rbservicios.demo_graphQL.infraestructure.adapter;

import com.rbservicios.demo_graphQL.application.event.port.OrderEventPublisher;
import com.rbservicios.demo_graphQL.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisherImpl implements OrderEventPublisher {

    @Override
    public void orderCreated(Order order) {
        // Luego continuamos
    }
}
