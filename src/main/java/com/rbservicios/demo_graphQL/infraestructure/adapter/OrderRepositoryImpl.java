package com.rbservicios.demo_graphQL.infraestructure.adapter;

import com.rbservicios.demo_graphQL.domain.model.Order;
import com.rbservicios.demo_graphQL.domain.repository.OrderRepository;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.OrderEntity;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.OrderMapper;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.JpaOrderRepository;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderRepositoryImpl(JpaOrderRepository jpaOrderRepository){
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = new OrderEntity(
                order.getUserId(),
                order.getCreatedAt()
        );
        return OrderMapper.toDomain(jpaOrderRepository.save(entity));
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return jpaOrderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDomain)
                .toList();
    }
}
