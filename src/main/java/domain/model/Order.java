package domain.model;

import java.time.Instant;
import java.util.List;

public class Order {

    private final Long id;
    private final Long userId;
    private final Instant createdAt;
    private final List<Long> productIds;

    public Order(Long id, Long userId, Instant createdAt, List<Long> productIds) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.productIds = productIds;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Instant getCreatedAt() { return createdAt; }
    public List<Long> getProductIds() { return productIds; }
}
