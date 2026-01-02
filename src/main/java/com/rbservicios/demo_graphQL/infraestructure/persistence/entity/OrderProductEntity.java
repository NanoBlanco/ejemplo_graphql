package com.rbservicios.demo_graphQL.infraestructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProductEntity {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private ProductEntity product;

    protected OrderProductEntity() {}

    public OrderProductEntity(OrderEntity order, ProductEntity product) {
        this.order = order;
        this.product = product;
        //this.id = new OrderProductId(order.getId(), product.getId());
    }

    public ProductEntity getProduct() {
        return product;
    }

}
