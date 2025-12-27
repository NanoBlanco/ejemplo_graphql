package domain.repository;

import domain.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findByIds(List<Long> ids);
}
