package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.DeleteProductCommand;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;

public class DeleteProductUseCase implements DeleteProductCommand {

    private final ProductRepository repository;

    public DeleteProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) {
        if(!repository.findById(id).isPresent()) {
            throw new RuntimeException("Product not found");
        }
        repository.deleteById(id);
    }
}
