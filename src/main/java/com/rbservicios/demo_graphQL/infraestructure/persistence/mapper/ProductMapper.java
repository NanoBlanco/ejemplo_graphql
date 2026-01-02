package com.rbservicios.demo_graphQL.infraestructure.persistence.mapper;

import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.ProductEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice()
        );
    }

    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        return entity;
    }
}
