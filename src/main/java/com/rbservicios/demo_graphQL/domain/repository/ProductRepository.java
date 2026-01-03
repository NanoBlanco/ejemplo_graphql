package com.rbservicios.demo_graphQL.domain.repository;

import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);
    List<Product> findAll();
    void deleteById(Long id);
    Optional<Product> findById(Long id);

    List<Product> findByIdIn(List<Long> ids);
}
