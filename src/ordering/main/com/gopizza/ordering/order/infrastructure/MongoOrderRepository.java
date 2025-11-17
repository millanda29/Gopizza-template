package com.gopizza.ordering.order.infrastructure;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.gopizza.ordering.order.domain.Order;
import com.gopizza.ordering.order.domain.OrderRepository;

@Repository
@AllArgsConstructor
public class MongoOrderRepository implements OrderRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Order save(Order order) {
        return mongoTemplate.save(order);
    }

    @Override
    public Order search(String id) {
        return mongoTemplate.findById(id, Order.class);
    }

    @Override
    public List<Order> searchAll() {
        return mongoTemplate.findAll(Order.class);
    }

}