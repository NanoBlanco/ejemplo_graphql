package infraestructure.persistence.repository;

import infraestructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity findByProductId(Long productId);
}
