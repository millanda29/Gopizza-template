package com.gopizza.apps.ordering.order;

import java.util.List;

import com.gopizza.shared.domain.order.OrderPizza;

public record CreateOrderRequest(
    String id,
    List<OrderPizza> pizzas
) {
}
