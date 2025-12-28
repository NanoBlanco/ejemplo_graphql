package adapter.graphql.resolver;

import adapter.graphql.DataLoaderNames;
import domain.model.Order;
import domain.model.User;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class OrderUserResolver {

    @SchemaMapping(typeName = "Order", field = "user")
    public CompletableFuture<User> user(
            Order order,
            DataFetchingEnvironment env
    ) {
        DataLoader<Long, User> loader = env.getDataLoader(DataLoaderNames.USER_BY_ID);

        return loader.load(order.getUserId());
    }
}
