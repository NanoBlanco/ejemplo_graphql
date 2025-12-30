package com.rbservicios.demo_graphQL.application.event;

public record OrderCreatedEvent(
        Long orderId,
        Long userId
) {
}
