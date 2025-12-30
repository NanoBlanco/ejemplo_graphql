package com.rbservicios.demo_graphQL.infraestructure.messaging.redis;

import com.rbservicios.demo_graphQL.application.command.port.OrderEventPublisher;
import com.rbservicios.demo_graphQL.application.event.OrderCreatedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisOrderEventPublisher implements OrderEventPublisher {

    private static final String CHANNEL = "order.created";
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisOrderEventPublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(OrderCreatedEvent event) {
        redisTemplate.convertAndSend(CHANNEL, event);
    }
}
