package application.event.port;

import domain.model.Order;

public interface OrderEventPublisher {
    void orderCreated(Order order);
}
