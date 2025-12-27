package adapter.graphql;

import domain.model.Order;
import domain.model.Product;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class OrderProductResolver {

    @SchemaMapping(typeName = "Order", field = "products")
    public CompletableFuture<List<Product>> products(
            Order order,
            DataFetchingEnvironment env
    ) {
        DataLoader<Long, Product> loader = env.getDataLoader(DataLoaderNames.PRODUCT_BY_ID);

        List<CompletableFuture<Product>> futures = order.getProductIds()
                .stream().map(loader::load)
                .toList();

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).thenApply(
                v->futures.stream()
                        .map(CompletableFuture::join)
                        .toList()
        );
    }

}
