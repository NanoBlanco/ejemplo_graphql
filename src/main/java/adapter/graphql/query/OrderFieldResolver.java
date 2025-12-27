package adapter.graphql.query;

import adapter.graphql.model.OrderGql;
import adapter.graphql.model.ProductGql;
import adapter.graphql.model.UserGql;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
