package com.rbservicios.demo_graphQL.adapter.graphql.mapper;

import com.rbservicios.demo_graphQL.adapter.graphql.model.ProductGql;
import com.rbservicios.demo_graphQL.domain.model.Product;

public class ProductGqlMapper {

    public static ProductGql fromDomain(Product product) {
        return new ProductGql(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
