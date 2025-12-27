package application.query.port;

import domain.model.Product;

import java.util.List;

public interface GetProductsByIdsQuery {

    List<Product> execute(List<Long> productIds);
}
