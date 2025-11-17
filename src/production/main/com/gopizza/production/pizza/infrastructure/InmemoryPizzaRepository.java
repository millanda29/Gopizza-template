package com.gopizza.production.pizza.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.gopizza.production.pizza.domain.Pizza;
import com.gopizza.production.pizza.domain.PizzaRepository;

@Repository
public class InmemoryPizzaRepository implements PizzaRepository {

    private final Map<String, Pizza> pizzas = new ConcurrentHashMap<>();

    @Override
    public Pizza save(Pizza pizza) {
        pizzas.put(pizza.id(), pizza);
        return pizza;
    }

    @Override
    public Pizza search(String id) {
        return pizzas.get(id);
    }

    @Override
    public List<Pizza> searchAll() {
        return new ArrayList<>(pizzas.values());
    }

}
