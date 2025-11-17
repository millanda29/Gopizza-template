package com.gopizza.shared.domain.order;

import java.time.Instant;

public record OrderCompletedEvent(
    String id,
    Instant completedAt
) {

    public static OrderCompletedEvent from(String id, Instant completedAt) {
        return new OrderCompletedEvent(id, completedAt);
    }
}
