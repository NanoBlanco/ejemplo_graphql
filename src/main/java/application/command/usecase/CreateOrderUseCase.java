package application.command.usecase;

import application.command.port.CreateOrderCommand;
import domain.model.Order;
import domain.repository.OrderRepository;

import java.time.Instant;
import java.util.List;

public class CreateOrderUseCase implements CreateOrderCommand {

    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order execute(Long userId, List<Long> productIds) {
        Order order = new Order(
                null,
                userId,
                Instant.now(),
                productIds
        );
        return orderRepository.save(order);
    }
}
