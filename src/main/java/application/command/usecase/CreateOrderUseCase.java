package application.command.usecase;

import application.command.port.CreateOrderCommand;
import application.event.port.OrderEventPublisher;
import domain.model.Order;
import domain.repository.OrderRepository;

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
