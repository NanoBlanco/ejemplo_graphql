package com.rbservicios.demo_graphQL.infraestructure.persistence.repository;

import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByIdIn(List<Long> ids);
}
