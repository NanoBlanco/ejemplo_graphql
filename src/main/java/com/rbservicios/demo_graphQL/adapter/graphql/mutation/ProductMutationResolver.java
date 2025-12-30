package com.rbservicios.demo_graphQL.adapter.graphql.mutation;

import com.rbservicios.demo_graphQL.adapter.graphql.mapper.ProductGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.ProductGql;
import com.rbservicios.demo_graphQL.adapter.graphql.resolver.BaseGraphQLResolver;
import com.rbservicios.demo_graphQL.application.command.port.CreateProductCommand;
import com.rbservicios.demo_graphQL.domain.model.Product;
import graphql.GraphQLException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMutationResolver extends BaseGraphQLResolver {

    private final CreateProductCommand command;

    public ProductMutationResolver(CreateProductCommand command) {
        this.command = command;
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
}
