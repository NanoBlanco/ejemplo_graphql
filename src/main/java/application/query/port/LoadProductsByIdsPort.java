package application.query.port;

import domain.model.Product;

import java.util.List;
import java.util.Map;

public interface LoadProductsByIdsPort {
    Map<Long, Product> loadByIds(List<Long> ids);
}
