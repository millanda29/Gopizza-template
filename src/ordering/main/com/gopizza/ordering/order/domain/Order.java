package com.gopizza.ordering.order.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gopizza.shared.domain.order.OrderPizza;
import com.gopizza.shared.domain.order.OrderStatus;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private OrderStatus status;
    private List<OrderPizza> pizzas;
    private double total;
    private Instant createdAt;
    private Instant completedAt;

    public Order(
        String id,
        OrderStatus status,
        List<OrderPizza> pizzas,
        Instant createdAt,
        Instant completedAt,
        double total
    ) {
        this.id = id;
        this.status = status;
        this.pizzas = pizzas = pizzas == null ? new ArrayList<>() : pizzas;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.total = total;
    }

    // Constructor vacío requerido por MongoDB
    public Order() {
        this.pizzas = new ArrayList<>();
    }

    public static Order create(
        String id,
        List<OrderPizza> pizzas
    ) {
        var total = pizzas.stream().mapToDouble(OrderPizza::price).sum();
        return new Order(
            id,
            OrderStatus.PROCESSING,
            pizzas,
            Instant.now(),
            null,
            total
        );
    }

    public void completePizza(String pizzaId) {
        if (isPizzaCompleted(pizzaId)) return;

        this.pizzas = this.pizzas.stream()
            .map(pizza -> pizza.id().equals(pizzaId) ? pizza.complete() : pizza)
            .toList();

        complete();
    }

    public boolean isPizzaCompleted(String id) {
        return pizzas.stream().anyMatch(pizza -> pizza.id().equals(id) && pizza.completed());
    }

    public void complete() {
        if (!isReadyToComplete()) return;
        this.status = OrderStatus.COMPLETED;
        this.completedAt = Instant.now();
    }

    public boolean isReadyToComplete() {
        return pizzas.stream().allMatch(OrderPizza::completed);
    }

    public String id() {
        return id;
    }

    public OrderStatus status() {
        return status;
    }

    public List<OrderPizza> items() {
        return pizzas;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant completedAt() {
        return completedAt;
    }

    public double total() {
        return total;
    }

}
