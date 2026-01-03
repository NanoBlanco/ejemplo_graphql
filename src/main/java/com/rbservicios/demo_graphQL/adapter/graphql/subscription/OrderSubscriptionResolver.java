package com.rbservicios.demo_graphQL.adapter.graphql.subscription;

import com.rbservicios.demo_graphQL.application.security.UserContext;
import com.rbservicios.demo_graphQL.adapter.graphql.model.OrderGql;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import com.rbservicios.demo_graphQL.infraestructure.messaging.redis.OrderEventSink;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class OrderSubscriptionResolver {

    private final OrderEventSink sink;

    public OrderSubscriptionResolver(OrderEventSink sink) {
        this.sink = sink;
    }

    public Flux<OrderGql> orderCreated(@ContextValue("userContext") UserContext userContext) {

        if (userContext == null) {
            throw new GraphQLException("Unauthorized");
        }

        return sink.flux()
                .filter(event -> event.userId().equals(userContext.getUserId()))
                .map(event -> OrderGql.fromId(event.orderId()));
    }
}
