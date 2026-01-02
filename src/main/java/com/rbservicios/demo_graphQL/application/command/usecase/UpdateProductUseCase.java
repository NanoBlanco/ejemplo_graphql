package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.UpdateProductCommand;
import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;
import java.math.BigDecimal;

public class UpdateProductUseCase implements UpdateProductCommand {

    private final ProductRepository repository;

    public UpdateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product execute(Long id, String name, BigDecimal price) {
        Product loaded = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));

        if(name != null){
            loaded.setName(name);
        }

        if(price != null) {
            loaded.setPrice(price);
        }

        return repository.save(loaded);
    }
}
