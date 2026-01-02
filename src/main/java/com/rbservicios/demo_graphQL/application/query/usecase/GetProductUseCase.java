package com.rbservicios.demo_graphQL.application.query.usecase;

import com.rbservicios.demo_graphQL.application.query.port.GetProductQuery;
import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;

import java.util.List;

public class GetProductUseCase implements GetProductQuery {

    private final ProductRepository repository;

    public GetProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> products() {
        return repository.findAll();
    }
}
