package com.rbservicios.demo_graphQL.infraestructure.adapter;

import com.rbservicios.demo_graphQL.domain.model.Order;
import com.rbservicios.demo_graphQL.domain.repository.OrderRepository;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.OrderEntity;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.ProductEntity;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.OrderMapper;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.JpaOrderRepository;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;
    private final ProductJpaRepository productJpaRepository;

    public OrderRepositoryImpl(
            JpaOrderRepository jpaOrderRepository,
            ProductJpaRepository productJpaRepository){
        this.jpaOrderRepository = jpaOrderRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = new OrderEntity(
                order.getUserId(),
                order.getCreatedAt()
        );
        // Carga productos
        List<ProductEntity> products = productJpaRepository
                .findByIdIn(order.getProductIds());

        // Asociar productos a la orden
        products.forEach(entity::addProduct);

        // Persistir
        OrderEntity saved = jpaOrderRepository.save(entity);

        return OrderMapper.toDomain(saved);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return jpaOrderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDomain)
                .toList();
    }
}
