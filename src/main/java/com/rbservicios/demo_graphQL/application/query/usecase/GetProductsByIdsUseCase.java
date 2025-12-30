package com.rbservicios.demo_graphQL.application.query.usecase;


import com.rbservicios.demo_graphQL.application.query.port.GetProductsByIdsQuery;
import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;

import java.util.List;

public class GetProductsByIdsUseCase implements GetProductsByIdsQuery {

    private final ProductRepository repository;

    public GetProductsByIdsUseCase(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public List<Product> execute(List<Long> productIds) {
        return repository.findByIds(productIds);
    }
}
