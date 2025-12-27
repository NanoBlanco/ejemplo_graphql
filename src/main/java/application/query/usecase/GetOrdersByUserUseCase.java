package application.query.usecase;

import application.query.port.GetOrdersByUserQuery;
import domain.model.Order;
import domain.repository.OrderRepository;

import java.util.List;

public class GetOrdersByUserUseCase implements GetOrdersByUserQuery {

    private final OrderRepository orderRepository;

    public GetOrdersByUserUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> execute(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
