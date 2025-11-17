package com.gopizza.shared.domain.order;

import com.gopizza.shared.domain.pizza.PizzaSize;
import com.gopizza.shared.domain.pizza.PizzaType;

public record OrderPizza(
    String id,
    PizzaType type,
    PizzaSize size,
    boolean completed,
    double price
) {
    public OrderPizza complete() {
        return new OrderPizza(id, type, size, true, price);
    }
}
