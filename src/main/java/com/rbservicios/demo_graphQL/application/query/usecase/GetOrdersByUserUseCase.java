package com.rbservicios.demo_graphQL.application.query.usecase;

import com.rbservicios.demo_graphQL.application.query.port.GetOrdersByUserQuery;
import com.rbservicios.demo_graphQL.domain.model.Order;
import com.rbservicios.demo_graphQL.domain.repository.OrderRepository;

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
