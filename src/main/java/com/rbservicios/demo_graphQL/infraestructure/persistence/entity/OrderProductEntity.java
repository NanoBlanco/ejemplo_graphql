package com.rbservicios.demo_graphQL.infraestructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProductEntity {

    @EmbeddedId
    private OrderProductId id = new OrderProductId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    protected OrderProductEntity() {}

    public OrderProductEntity(OrderEntity order, ProductEntity product) {
        this.order = order;
        this.product = product;
    }

    public ProductEntity getProduct() {
        return product;
    }

}
