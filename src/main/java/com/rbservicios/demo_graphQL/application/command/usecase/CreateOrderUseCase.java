package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.CreateOrderCommand;
import com.rbservicios.demo_graphQL.application.event.port.OrderEventPublisher;
import com.rbservicios.demo_graphQL.domain.model.Order;
import com.rbservicios.demo_graphQL.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.util.List;

public class CreateOrderUseCase implements CreateOrderCommand {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher eventPublisher;

    public CreateOrderUseCase(
            OrderRepository orderRepository,
            OrderEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Order execute(Long userId, List<Long> productIds) {

        Order order = new Order(
                null,
                userId,
                Instant.now(),
                productIds
        );

        Order saved = orderRepository.save(order);

        // Emision del evento
        eventPublisher.orderCreated(order);

        return saved;
    }
}
