package infraestructure.persistence.mapper;

import domain.model.Product;
import infraestructure.persistence.entity.ProductEntity;

public class ProductMapper {

    public Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice()
        );
    }
}
