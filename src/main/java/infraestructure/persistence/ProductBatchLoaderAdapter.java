package infraestructure.persistence;

import application.query.port.LoadProductsByIdsPort;
import domain.model.Product;
import domain.repository.ProductRepository;
import infraestructure.persistence.mapper.ProductMapper;
import infraestructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductBatchLoaderAdapter implements LoadProductsByIdsPort {

    private final ProductJpaRepository repository;
    private final ProductMapper mapper;

    public ProductBatchLoaderAdapter(ProductJpaRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Map<Long, Product> loadByIds(List<Long> ids) {
        return repository.findByIdIn(ids).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
