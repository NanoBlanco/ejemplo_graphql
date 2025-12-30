package com.rbservicios.demo_graphQL.application.query.port;

import com.rbservicios.demo_graphQL.domain.model.Product;

import java.util.List;
import java.util.Map;

public interface LoadProductsByIdsPort {
    Map<Long, Product> loadByIds(List<Long> ids);
}
