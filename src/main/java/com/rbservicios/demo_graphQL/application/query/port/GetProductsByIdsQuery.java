package com.rbservicios.demo_graphQL.application.query.port;

import com.rbservicios.demo_graphQL.domain.model.Product;

import java.util.List;

public interface GetProductsByIdsQuery {

    List<Product> execute(List<Long> productIds);
}
