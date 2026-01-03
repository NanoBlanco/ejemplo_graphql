package com.rbservicios.demo_graphQL.adapter.graphql.query;

import com.rbservicios.demo_graphQL.adapter.graphql.mapper.ProductGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.mapper.UserGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.OrderGql;
import com.rbservicios.demo_graphQL.adapter.graphql.model.ProductGql;
import com.rbservicios.demo_graphQL.adapter.graphql.model.UserGql;
import com.rbservicios.demo_graphQL.application.query.port.LoadProductsByIdsPort;
import com.rbservicios.demo_graphQL.application.query.port.LoadUsersByIdsPort;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.UserMapper;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class OrderFieldResolver {

    public final LoadUsersByIdsPort loadUsersByIds;
    public final LoadProductsByIdsPort loadProductsByIds;

    public OrderFieldResolver(LoadUsersByIdsPort loadUsersByIds, LoadProductsByIdsPort loadProductsByIds) {
        this.loadUsersByIds = loadUsersByIds;
        this.loadProductsByIds = loadProductsByIds;
    }

    @BatchMapping(typeName = "Order", field = "user")
    public Mono<Map<OrderGql, UserGql>> user(List<OrderGql> orders) {

        List<Long> userIds = orders.stream()
                .map(OrderGql::userId)
                .distinct()
                .toList();

        return Mono.fromCallable(()-> loadUsersByIds.loadByIds(userIds))
                .subscribeOn(Schedulers.boundedElastic())
                .map(users -> {
                    // Convierte User (domain) -> UserGql
                    Map<Long, UserGql> userMap = users.entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    entry -> UserGqlMapper.fromDomain(entry.getValue())
                            ));

                    return orders.stream()
                            .collect(Collectors.toMap(Function.identity(), order-> userMap.get(order.userId())));
                });
    }

    @BatchMapping(typeName = "Order", field = "products")
    public Mono<Map<OrderGql, List<ProductGql>>> products(List<OrderGql> orders){

        // Extrae todos los Ids de productos únicos
        List<Long> allProductIds = orders.stream()
                .flatMap(order -> order.productsIds().stream())
                .distinct()
                .toList();

        return Mono.fromCallable(()-> loadProductsByIds.loadByIds(allProductIds))
                .subscribeOn(Schedulers.boundedElastic())
                .map(products -> {
                   Map<Long, ProductGql> productMap = products.entrySet().stream()
                           .collect(Collectors.toMap(Map.Entry::getKey, entry -> ProductGqlMapper.fromDomain(entry.getValue())
                           ));

                   return orders.stream()
                           .collect(Collectors.toMap(
                                   Function.identity(),
                                   order -> order.productsIds().stream()
                                           .map(productMap::get)
                                           .filter(Objects::nonNull)
                                           .toList()
                           ));
                });
    }
}
