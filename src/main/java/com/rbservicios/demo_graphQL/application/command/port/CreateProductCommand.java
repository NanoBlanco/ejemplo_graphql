package com.rbservicios.demo_graphQL.application.command.port;

import com.rbservicios.demo_graphQL.domain.model.Product;
import java.math.BigDecimal;

public interface CreateProductCommand {

    Product execute(String name, BigDecimal price);
}
