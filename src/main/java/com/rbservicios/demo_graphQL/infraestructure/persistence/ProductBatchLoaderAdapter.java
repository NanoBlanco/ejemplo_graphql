package com.rbservicios.demo_graphQL.infraestructure.persistence;

import com.rbservicios.demo_graphQL.application.query.port.LoadProductsByIdsPort;
import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.ProductMapper;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductBatchLoaderAdapter implements LoadProductsByIdsPort {

    private final ProductJpaRepository repository;

    public ProductBatchLoaderAdapter(ProductJpaRepository repository, ProductMapper mapper) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Product> loadByIds(List<Long> ids) {
        return repository.findByIdIn(ids).stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
