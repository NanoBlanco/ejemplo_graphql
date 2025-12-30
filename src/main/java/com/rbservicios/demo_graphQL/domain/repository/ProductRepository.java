package com.rbservicios.demo_graphQL.domain.repository;

import com.rbservicios.demo_graphQL.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findByIds(List<Long> ids);
}
