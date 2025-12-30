package com.rbservicios.demo_graphQL.infraestructure.persistence.repository;

import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUserId(Long userId);

}
