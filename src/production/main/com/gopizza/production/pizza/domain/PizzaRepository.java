package com.gopizza.production.pizza.domain;

import java.util.List;

public interface PizzaRepository {
    Pizza save(Pizza pizza);

    Pizza search(String id);

    List<Pizza> searchAll();
}
