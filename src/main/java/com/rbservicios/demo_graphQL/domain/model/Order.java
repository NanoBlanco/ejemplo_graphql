package com.rbservicios.demo_graphQL.domain.model;

import java.time.Instant;
import java.util.List;

public class Order {

    private  Long id;
    private  Long userId;
    private  Instant createdAt;
    private  List<Long> productIds;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
