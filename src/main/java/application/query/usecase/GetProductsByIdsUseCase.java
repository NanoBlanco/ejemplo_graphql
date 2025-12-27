package application.query.usecase;


import application.query.port.GetProductsByIdsQuery;
import domain.model.Product;
import domain.repository.ProductRepository;

import java.util.List;

public class GetProductsByIdsUseCase implements GetProductsByIdsQuery {

    private final ProductRepository productRepository;

    public GetProductsByIdsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> execute(List<Long> productIds) {
        return List.of();
    }
}
