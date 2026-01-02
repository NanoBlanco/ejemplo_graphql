package com.rbservicios.demo_graphQL.application.query.port;

import com.rbservicios.demo_graphQL.domain.model.Product;

import java.util.List;

public interface GetProductQuery {
    Product getProductById(Long id);
    List<Product> products();
}
