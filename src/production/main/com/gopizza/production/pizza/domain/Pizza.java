package com.gopizza.production.pizza.domain;

import java.time.Instant;
import java.util.List;

import com.gopizza.shared.domain.pizza.PizzaIngredient;
import com.gopizza.shared.domain.pizza.PizzaIngredientsMapper;
import com.gopizza.shared.domain.pizza.PizzaSize;
import com.gopizza.shared.domain.pizza.PizzaType;

public class Pizza {

    private String id;
    private String orderId;
    private PizzaType type;
    private PizzaSize size;
    private List<PizzaIngredient> ingredients;
    private int creationTimeMinutes;
    private Instant createdAt;

    public Pizza(
        String id,
        String orderId,
        PizzaType type,
        PizzaSize size,
        List<PizzaIngredient> ingredients,
        int creationTimeMinutes,
        Instant createdAt
    ) {
        this.id = id;
        this.orderId = orderId;
        this.type = type;
        this.size = size;
        this.ingredients = ingredients;
        this.creationTimeMinutes = creationTimeMinutes;
        this.createdAt = createdAt;
    }

    public static Pizza create(
        String id,
        String orderId,
        PizzaType type,
        PizzaSize size,
        int creationTimeMinutes
    ) {
        return new Pizza(
            id,
            orderId,
            type,
            size,
            PizzaIngredientsMapper.generate(type),
            creationTimeMinutes,
            Instant.now()
        );
    }

    public String id() {
        return id;
    }

    public String orderId() {
        return orderId;
    }

    public PizzaType type() {
        return type;
    }

    public PizzaSize size() {
        return size;
    }

    public List<PizzaIngredient> ingredients() {
        return ingredients;
    }

    public int creationTimeMinutes() {
        return creationTimeMinutes;
    }

    public Instant createdAt() {
        return createdAt;
    }

}
