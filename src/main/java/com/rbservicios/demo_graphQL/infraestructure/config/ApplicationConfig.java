package com.rbservicios.demo_graphQL.infraestructure.config;

import com.rbservicios.demo_graphQL.application.command.port.*;
import com.rbservicios.demo_graphQL.application.command.usecase.*;
import com.rbservicios.demo_graphQL.application.event.port.OrderEventPublisher;
import com.rbservicios.demo_graphQL.application.query.port.GetOrdersByUserQuery;
import com.rbservicios.demo_graphQL.application.query.port.GetProductQuery;
import com.rbservicios.demo_graphQL.application.query.port.GetProductsByIdsQuery;
import com.rbservicios.demo_graphQL.application.query.port.GetUserQuery;
import com.rbservicios.demo_graphQL.application.query.usecase.GetOrdersByUserUseCase;
import com.rbservicios.demo_graphQL.application.query.usecase.GetProductUseCase;
import com.rbservicios.demo_graphQL.application.query.usecase.GetProductsByIdsUseCase;
import com.rbservicios.demo_graphQL.application.query.usecase.GetUserUseCase;
import com.rbservicios.demo_graphQL.domain.repository.OrderRepository;
import com.rbservicios.demo_graphQL.domain.repository.ProductRepository;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    CreateUserCommand createUserCommand(UserRepository repository) {
        return new CreateUserUserCase(repository);
    }

    @Bean
    UpdateUserCommand updateUserCommand(UserRepository repository) { return new UpdateUserUseCase(repository); }

    @Bean
    GetUserQuery getUserQuery(UserRepository repository) { return new GetUserUseCase(repository); }

    @Bean
    DeleteUserCommand deleteUserCommand(UserRepository repository) {return new DeleteUserUseCase(repository); }

    @Bean
    CreateProductCommand createProductCommand(ProductRepository prodRepository) {
        return new CreateProductUseCase(prodRepository);
    }

    @Bean
    GetProductQuery getProductQuery(ProductRepository repository) {return new GetProductUseCase(repository); }

    @Bean
    UpdateProductCommand updateProductCommand(ProductRepository repository) {
        return new UpdateProductUseCase(repository);
    }

    @Bean
    DeleteProductCommand deleteProductCommand(ProductRepository repository) {
        return new DeleteProductUseCase(repository);
    }

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
    LoginUseCase loginUseCase(UserRepository repository, JwtTokenProvider jwtTokenProvider) {
        return new LoginUseCase(repository, jwtTokenProvider);
    }

}
