package infraestructure.config;

import application.command.port.CreateOrderCommand;
import application.command.usecase.CreateOrderUseCase;
import application.event.port.OrderEventPublisher;
import application.query.port.GetOrdersByUserQuery;
import application.query.port.GetProductsByIdsQuery;
import application.query.usecase.GetOrdersByUserUseCase;
import application.query.usecase.GetProductsByIdsUseCase;
import domain.repository.OrderRepository;
import domain.repository.ProductRepository;
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
}
