package infraestructure.messaging.redis;

import application.command.port.OrderEventPublisher;
import application.event.OrderCreatedEvent;
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
