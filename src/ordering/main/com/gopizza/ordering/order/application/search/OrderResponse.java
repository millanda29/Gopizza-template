package com.gopizza.ordering.order.application.search;

import java.time.Instant;
import java.util.List;

import com.gopizza.ordering.order.domain.Order;
import com.gopizza.shared.domain.order.OrderPizza;
import com.gopizza.shared.domain.order.OrderStatus;

public record OrderResponse(
    String id,
    OrderStatus status,
    List<OrderPizza> pizzas,
    Instant createdAt,
    Instant endedAt,
    double total
) {

    public static List<OrderResponse> from(List<Order> orders) {
        return orders.stream().map(OrderResponse::from).toList();
    }

    public static OrderResponse from(Order order) {
        return new OrderResponse(
            order.id(),
            order.status(),
            order.items(),
            order.createdAt(),
            order.completedAt(),
            order.total()
        );
    }

}
