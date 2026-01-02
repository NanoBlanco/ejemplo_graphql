package com.rbservicios.demo_graphQL.adapter.graphql.query;

import com.rbservicios.demo_graphQL.adapter.graphql.mapper.ProductGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.ProductGql;
import com.rbservicios.demo_graphQL.application.query.port.GetProductQuery;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductQueryResolver {

    private final GetProductQuery query;

    public ProductQueryResolver(GetProductQuery query) {
        this.query = query;
    }

    @QueryMapping
    public ProductGql productById(@Argument Long id) {
        return ProductGqlMapper.fromDomain(query.getProductById(id));
    }

    @QueryMapping
    public List<ProductGql> products() {
        return query.products()
                .stream()
                .map(ProductGqlMapper::fromDomain)
                .toList();
    }
}
