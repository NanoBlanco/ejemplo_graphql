package com.rbservicios.demo_graphQL.infraestructure.messaging.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisListenerConfig {

    private final RedisConnectionFactory factory;
    private final RedisOrderEventListener listener;

    public RedisListenerConfig(RedisConnectionFactory factory, RedisOrderEventListener listener) {
        this.factory = factory;
        this.listener = listener;
    }

    @Bean
    public RedisMessageListenerContainer container() {

        RedisMessageListenerContainer container =
                new RedisMessageListenerContainer();

        container.setConnectionFactory(factory);
        container.addMessageListener(
                listener,
                new ChannelTopic("order.created")
        );

        return container;
    }
}
