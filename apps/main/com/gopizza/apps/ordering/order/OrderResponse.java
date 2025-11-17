package com.gopizza.apps.ordering.order;

import java.time.Instant;
import java.util.List;

import com.gopizza.shared.domain.order.OrderPizza;

public record OrderResponse(
    String id,
    String status,
    List<OrderPizza> pizzas,
    Double total,
    Instant createdAt
) {
}