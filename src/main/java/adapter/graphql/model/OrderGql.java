package adapter.graphql.model;

import java.util.List;

public record OrderGql(
        Long id,
        String createdAt,
        Long userId,
        List<Long> productsIds
) {
    public static OrderGql fromId(Long orderId) {
        return new OrderGql(orderId, null, null, List.of());
    }
}
