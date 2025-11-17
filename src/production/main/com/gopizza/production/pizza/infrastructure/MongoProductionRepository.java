package com.gopizza.production.pizza.infrastructure;

import com.gopizza.production.pizza.domain.Pizza;
import com.gopizza.production.pizza.domain.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MongoProductionRepository implements PizzaRepository {

    private final MongoTemplate mongoTemplate;


    @Override
    public Pizza save(Pizza pizza) {
        return mongoTemplate.save(pizza);
    }

    @Override
    public Pizza search(String id) {
        return mongoTemplate.findById(id, Pizza.class);
    }

    @Override
    public List<Pizza> searchAll() {
        return mongoTemplate.findAll(Pizza.class);
    }
}
