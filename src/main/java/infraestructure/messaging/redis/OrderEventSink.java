package infraestructure.messaging.redis;

import application.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class OrderEventSink {
    private final Sinks.Many<OrderCreatedEvent> sink =
            Sinks.many().multicast().onBackpressureBuffer();

    public void emit(OrderCreatedEvent event) {
        sink.tryEmitNext(event);
    }

    public Flux<OrderCreatedEvent> flux() {
        return sink.asFlux();
    }
}
