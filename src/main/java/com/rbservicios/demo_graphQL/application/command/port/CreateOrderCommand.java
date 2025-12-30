package com.rbservicios.demo_graphQL.application.command.port;

import com.rbservicios.demo_graphQL.domain.model.Order;

import java.util.List;

public interface CreateOrderCommand {

    Order execute(Long userId, List<Long> productIds);
}
