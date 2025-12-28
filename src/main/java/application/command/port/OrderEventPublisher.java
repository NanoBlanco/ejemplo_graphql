package application.command.port;

import application.event.OrderCreatedEvent;

public interface OrderEventPublisher {
    void publish(OrderCreatedEvent event);
}
