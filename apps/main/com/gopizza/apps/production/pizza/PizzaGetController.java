package com.gopizza.apps.production.pizza;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopizza.production.pizza.application.search.PizzaResponse;
import com.gopizza.production.pizza.application.search.PizzaSearcher;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
public class PizzaGetController {

    private final PizzaSearcher searcher;

    @GetMapping("/{id}")
    public ResponseEntity<PizzaResponse> search(@PathVariable String id) {
        var pizza = searcher.search(id);
        if (pizza == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(PizzaResponse.from(pizza));
    }

    @GetMapping
    public ResponseEntity<List<PizzaResponse>> searchAll() {
        var pizzas = searcher.searchAll();
        return ResponseEntity.ok(PizzaResponse.from(pizzas));
    }

}