package com.rbservicios.demo_graphQL.adapter.graphql.query;

import com.rbservicios.demo_graphQL.adapter.graphql.model.OrderGql;
import com.rbservicios.demo_graphQL.adapter.graphql.model.ProductGql;
import com.rbservicios.demo_graphQL.adapter.graphql.model.UserGql;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderFieldResolver {

    @SchemaMapping(typeName = "Order", field = "user")
    public UserGql user(
            OrderGql order,
            DataLoader<Long, UserGql> userLoader) {
        return userLoader.load(order.userId()).join();
    }

    @SchemaMapping(typeName = "Order", field = "products")
    public List<ProductGql> products(
            OrderGql order,
            DataLoader<Long, ProductGql> productLoader) {
        return order.productsIds().stream()
                .map(productLoader::load)
                .map(java.util.concurrent.CompletableFuture::join)
                .toList();
    }
}
