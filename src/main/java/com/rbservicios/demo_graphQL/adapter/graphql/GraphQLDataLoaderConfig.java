package com.rbservicios.demo_graphQL.adapter.graphql;

import com.rbservicios.demo_graphQL.application.query.port.LoadProductsByIdsPort;
import com.rbservicios.demo_graphQL.application.query.port.LoadUsersByIdsPort;
import com.rbservicios.demo_graphQL.domain.model.Product;
import com.rbservicios.demo_graphQL.domain.model.User;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Configuration
public class GraphQLDataLoaderConfig {

    private final LoadUsersByIdsPort loadUsersByIds;
    private final LoadProductsByIdsPort loadProductsByIds;

    public GraphQLDataLoaderConfig(
            LoadUsersByIdsPort loadUsersByIds,
            LoadProductsByIdsPort loadProductsByIds) {
        this.loadUsersByIds = loadUsersByIds;
        this.loadProductsByIds = loadProductsByIds;
    }

    @Bean
    public DataLoaderRegistry dataLoaderRegistry() {

        MappedBatchLoader<Long, User> userBatchLoader = keys -> CompletableFuture.supplyAsync(()->loadUsersByIds.loadByIds(new ArrayList<>(keys)));

        MappedBatchLoader<Long, Product> productBatchLoader = keys -> CompletableFuture.supplyAsync(()-> loadProductsByIds.loadByIds(new ArrayList<>()));

        DataLoader<Long, User> userLoader = DataLoaderFactory.newMappedDataLoader(userBatchLoader);

        DataLoader<Long, Product> productLoader = DataLoaderFactory.newMappedDataLoader(productBatchLoader);

        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register(DataLoaderNames.USER_BY_ID, userLoader);
        registry.register(DataLoaderNames.PRODUCT_BY_ID, productLoader);

        return registry;
    }
}
