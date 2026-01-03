package com.rbservicios.demo_graphQL.application.command.port;

public interface DeleteOrderCommand {
    boolean deleteOrder(Long orderId);
}
