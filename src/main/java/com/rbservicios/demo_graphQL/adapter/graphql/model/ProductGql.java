package com.rbservicios.demo_graphQL.adapter.graphql.model;

public record ProductGql(
        Long id,
        String name,
        java.math.BigDecimal price
) {
}
