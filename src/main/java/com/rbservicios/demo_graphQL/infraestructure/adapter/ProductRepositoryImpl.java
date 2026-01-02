package com.rbservicios.demo_graphQL.infraestructure.adapter;

import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.ProductEntity;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.ProductMapper;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    public ProductRepositoryImpl(ProductJpaRepository jpaRepository) { this.jpaRepository = jpaRepository; }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id).map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream().map(ProductMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Product> findByIdIn(List<Long> ids) {
        return jpaRepository.findByIdIn(ids).stream().map(ProductMapper::toDomain).toList();
    }
}
