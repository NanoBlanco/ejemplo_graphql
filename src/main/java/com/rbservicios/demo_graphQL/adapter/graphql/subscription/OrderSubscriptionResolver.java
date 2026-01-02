package com.rbservicios.demo_graphQL.adapter.graphql.subscription;

import com.rbservicios.demo_graphQL.application.security.UserContext;
import com.rbservicios.demo_graphQL.adapter.graphql.model.OrderGql;
import com.rbservicios.demo_graphQL.adapter.graphql.resolver.BaseGraphQLResolver;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import com.rbservicios.demo_graphQL.infraestructure.messaging.redis.OrderEventSink;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class OrderSubscriptionResolver extends BaseGraphQLResolver {

    private final OrderEventSink sink;

    public OrderSubscriptionResolver(OrderEventSink sink) {
        this.sink = sink;
    }

    public Flux<OrderGql> orderCreated(DataFetchingEnvironment env) {

        UserContext user = getUserContext(env);

        if (user == null) {
            throw new GraphQLException("Unauthorized");
        }

        return sink.flux()
                .filter(event -> event.userId().equals(user.getUserId()))
                .map(event -> OrderGql.fromId(event.orderId()));
    }
}
