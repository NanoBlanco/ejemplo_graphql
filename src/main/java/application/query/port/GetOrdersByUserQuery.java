package application.query.port;

import domain.model.Order;

import java.util.List;

public interface GetOrdersByUserQuery {

    List<Order> execute(Long userId);
}
