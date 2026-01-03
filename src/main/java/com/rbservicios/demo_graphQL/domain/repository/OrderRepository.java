package com.rbservicios.demo_graphQL.domain.repository;

import com.rbservicios.demo_graphQL.domain.model.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);
    List<Order> findByUserId(Long userId);
    void deleteById(Long orderId);
    boolean existsById(Long userId);
}
