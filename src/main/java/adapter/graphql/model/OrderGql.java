package adapter.graphql.model;

import java.util.List;

public record OrderGql(
        Long id,
        String createdAt,
        Long userId,
        List<Long> productsIds
) {
}
