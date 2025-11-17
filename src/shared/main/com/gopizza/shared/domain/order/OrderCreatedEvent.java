package com.gopizza.shared.domain.order;

import java.util.List;

public record OrderCreatedEvent(
    String id,
    List<OrderPizza> pizzas
) {
    public static OrderCreatedEvent from(String id, List<OrderPizza> pizzas) {
        return new OrderCreatedEvent(id, pizzas);
    }

}