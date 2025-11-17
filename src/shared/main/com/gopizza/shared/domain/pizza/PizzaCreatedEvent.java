package com.gopizza.shared.domain.pizza;

import java.time.Instant;

public record PizzaCreatedEvent(
    String id,
    String orderId,
    PizzaType type,
    PizzaSize size,
    Instant createdAt
) {
    public static PizzaCreatedEvent from(
        String id,
        String orderId,
        PizzaType type,
        PizzaSize size,
        Instant createdAt
    ) {
        return new PizzaCreatedEvent(id, orderId, type, size, createdAt);
    }
}
