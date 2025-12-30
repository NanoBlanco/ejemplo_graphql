package com.rbservicios.demo_graphQL.infraestructure.adapter;

import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.ProductEntity;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.ProductMapper;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.ProductJpaRepository;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    public ProductRepositoryImpl(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {

        ProductEntity entity = new ProductEntity(
                product.getName(),
                product.getPrice()
        );

        return ProductMapper.toDomain(entity);
    }

    @Override
    public List<Product> findByIds(List<Long> ids) {
        return List.of();
    }
}
