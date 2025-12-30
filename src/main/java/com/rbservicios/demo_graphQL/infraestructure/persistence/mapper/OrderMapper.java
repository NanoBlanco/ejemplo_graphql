package com.rbservicios.demo_graphQL.infraestructure.persistence.mapper;

import com.rbservicios.demo_graphQL.domain.model.Order;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.OrderEntity;

import java.util.Collections;
import java.util.List;

public class OrderMapper {

    public static Order toDomain(OrderEntity entity) {

        List<Long> productIds = entity.getProducts() == null
                ? Collections.emptyList()
                : entity.getProducts().stream()
                .map(op -> op.getProduct().getId())
                .toList();

        return new Order(
                entity.getId(),
                entity.getUserId(),
                entity.getCreatedAt(),
                entity.getProducts()
                        .stream()
                        .map(op -> op.getProduct().getId())
                        .toList()
        );
    }
}
