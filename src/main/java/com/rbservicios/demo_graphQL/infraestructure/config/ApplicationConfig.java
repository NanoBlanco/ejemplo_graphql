package com.rbservicios.demo_graphQL.infraestructure.config;

import com.rbservicios.demo_graphQL.application.command.port.CreateOrderCommand;
import com.rbservicios.demo_graphQL.application.command.port.CreateUserCommand;
import com.rbservicios.demo_graphQL.application.command.usecase.CreateOrderUseCase;
import com.rbservicios.demo_graphQL.application.command.usecase.CreateUserUserCase;
import com.rbservicios.demo_graphQL.application.event.port.OrderEventPublisher;
import com.rbservicios.demo_graphQL.application.query.port.GetOrdersByUserQuery;
import com.rbservicios.demo_graphQL.application.query.port.GetProductsByIdsQuery;
import com.rbservicios.demo_graphQL.application.query.usecase.GetOrdersByUserUseCase;
import com.rbservicios.demo_graphQL.application.query.usecase.GetProductsByIdsUseCase;
import com.rbservicios.demo_graphQL.domain.repository.OrderRepository;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    CreateOrderCommand createOrderCommand(OrderRepository orderRepository, OrderEventPublisher eventPublisher) {
        return new CreateOrderUseCase(orderRepository, eventPublisher);
    }

    @Bean
    GetOrdersByUserQuery getOrdersByUserQuery(OrderRepository orderRepository) {
        return new GetOrdersByUserUseCase(orderRepository);
    }

    @Bean
    GetProductsByIdsQuery getProductsByIdsQuery(ProductRepository productRepository) {
        return new GetProductsByIdsUseCase(productRepository);
    }

    @Bean
    CreateUserCommand createUserCommand(UserRepository repository) {
        return new CreateUserUserCase(repository);
    }
}
