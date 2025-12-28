package infraestructure.messaging.redis;

import application.event.OrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisOrderEventListener implements MessageListener {

    private final OrderEventSink sink;
    private final ObjectMapper mapper;

    public RedisOrderEventListener(OrderEventSink sink, ObjectMapper mapper) {
        this.sink = sink;
        this.mapper = mapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            OrderCreatedEvent event =
                    mapper.readValue(message.getBody(), OrderCreatedEvent.class);

            sink.emit(event);
        } catch (Exception e) {

        }
    }
}
