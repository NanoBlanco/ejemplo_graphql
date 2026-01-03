package com.rbservicios.demo_graphQL.adapter.graphql.controller;

import com.rbservicios.demo_graphQL.adapter.graphql.mapper.ProductGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.ProductGql;
import com.rbservicios.demo_graphQL.application.command.port.CreateProductCommand;
import com.rbservicios.demo_graphQL.application.command.port.DeleteProductCommand;
import com.rbservicios.demo_graphQL.application.command.port.UpdateProductCommand;
import com.rbservicios.demo_graphQL.domain.model.Product;
import graphql.GraphQLException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class ProductController {

    private final CreateProductCommand command;
    private final UpdateProductCommand updateCommand;
    private final DeleteProductCommand deleteCommand;

    public ProductController(
            CreateProductCommand command,
            UpdateProductCommand updateCommand,
            DeleteProductCommand deleteCommand
    ) {
        this.command = command;
        this.updateCommand = updateCommand;
        this.deleteCommand = deleteCommand;
    }

    @MutationMapping
    public ProductGql createProduct(
            @Argument String name,
            @Argument BigDecimal price
    ) {
        Product prod = command.execute(name, price);

        if (prod == null) {
            throw new GraphQLException("Product creation failed");
        }

        return ProductGqlMapper.fromDomain(prod);
    }

    @MutationMapping
    public ProductGql updateProduct(
            @Argument Long id,
            @Argument String name,
            @Argument BigDecimal price
    ) {
        return ProductGqlMapper.fromDomain(
                updateCommand.execute(id, name, price)
        );
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        deleteCommand.execute(id);
        return true;
    }
}
