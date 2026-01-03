package com.rbservicios.demo_graphQL.domain.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long orderId){
        super("Order with id "+orderId+" not found");
    }
}
