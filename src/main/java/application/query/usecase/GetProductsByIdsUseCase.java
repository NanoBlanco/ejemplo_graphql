package application.query.usecase;


import application.query.port.GetProductsByIdsQuery;
import domain.model.Product;
import domain.repository.ProductRepository;

import java.util.List;

public class GetProductsByIdsUseCase implements GetProductsByIdsQuery {

    private final ProductRepository repository;

    public GetProductsByIdsUseCase(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public List<Product> execute(List<Long> productIds) {
        return repository.findByIds(productIds);
    }
}
