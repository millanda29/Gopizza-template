package com.gopizza.production.pizza.application.search;

import java.time.Instant;
import java.util.List;

import com.gopizza.production.pizza.domain.Pizza;
import com.gopizza.shared.domain.pizza.PizzaIngredient;
import com.gopizza.shared.domain.pizza.PizzaSize;
import com.gopizza.shared.domain.pizza.PizzaType;

public record PizzaResponse(
    String id,
    String orderId,
    PizzaType type,
    PizzaSize size,
    List<PizzaIngredient> ingredients,
    int creationTimeMinutes,
    Instant createdAt
) {

    public static List<PizzaResponse> from(List<Pizza> pizzas) {
        return pizzas.stream().map(PizzaResponse::from).toList();
    }

    public static PizzaResponse from(Pizza pizza) {
        return new PizzaResponse(
            pizza.id(),
            pizza.orderId(),
            pizza.type(),
            pizza.size(),
            pizza.ingredients(),
            pizza.creationTimeMinutes(),
            pizza.createdAt()
        );
    }

}
