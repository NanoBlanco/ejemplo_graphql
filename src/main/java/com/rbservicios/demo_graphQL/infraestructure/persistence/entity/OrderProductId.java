package com.rbservicios.demo_graphQL.infraestructure.persistence.entity;

import java.io.Serializable;

public class OrderProductId implements Serializable {

    private Long orderId;
    private Long productId;

    protected OrderProductId() {}

    public OrderProductId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        OrderProductId that = (OrderProductId) o;
        return orderId.equals(that.orderId) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }
}
