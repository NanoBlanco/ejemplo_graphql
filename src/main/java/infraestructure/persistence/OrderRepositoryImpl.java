package infraestructure.persistence;

import domain.model.Order;
import domain.repository.OrderRepository;
import infraestructure.persistence.entity.OrderEntity;
import infraestructure.persistence.mapper.OrderMapper;
import infraestructure.persistence.repository.JpaOrderRepository;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderRepositoryImpl(JpaOrderRepository jpaOrderRepository){
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = new OrderEntity(
                order.getUserId(),
                order.getCreatedAt()
        );
        return OrderMapper.toDomain(jpaOrderRepository.save(entity));
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return jpaOrderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDomain)
                .toList();
    }
}
