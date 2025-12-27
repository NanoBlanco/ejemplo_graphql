package application.command.port;

import domain.model.Order;

import java.util.List;

public interface CreateOrderCommand {

    Order execute(Long userId, List<Long> productIds);
}
