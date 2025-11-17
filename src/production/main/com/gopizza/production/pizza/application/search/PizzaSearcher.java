package com.gopizza.production.pizza.application.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gopizza.production.pizza.domain.Pizza;
import com.gopizza.production.pizza.domain.PizzaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaSearcher {

    private final PizzaRepository repository;

    public Pizza search(String id) {
        return repository.search(id);
    }

    public List<Pizza> searchAll() {
        return repository.searchAll();
    }

}
