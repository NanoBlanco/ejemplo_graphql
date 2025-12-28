package application.event;

public record OrderCreatedEvent(
        Long orderId,
        Long userId
) {
}
