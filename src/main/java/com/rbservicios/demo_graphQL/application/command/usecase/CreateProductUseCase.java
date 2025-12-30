package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.CreateProductCommand;
import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;

import java.math.BigDecimal;

public class CreateProductUseCase implements CreateProductCommand {

    private final ProductRepository repository;

    public CreateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product execute(String name, BigDecimal price) {
        Product new_product = new Product(
                null,
                name,
                price
        );

        return repository.save(new_product);
    }
}
