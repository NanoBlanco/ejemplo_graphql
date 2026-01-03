package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.DeleteOrderCommand;
import com.rbservicios.demo_graphQL.domain.exception.OrderNotFoundException;
import com.rbservicios.demo_graphQL.domain.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

public class DeleteOrderUseCase implements DeleteOrderCommand {

    private final OrderRepository repository;

    public DeleteOrderUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public boolean deleteOrder(Long orderId) {
        if(!repository.existsById(orderId)){
            throw new OrderNotFoundException(orderId);
        }

        repository.deleteById(orderId);

        return true;
    }

}
