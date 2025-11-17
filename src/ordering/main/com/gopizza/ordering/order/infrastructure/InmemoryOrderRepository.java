package com.gopizza.ordering.order.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.gopizza.ordering.order.domain.Order;
import com.gopizza.ordering.order.domain.OrderRepository;

@Repository
public class InmemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    @Override
    public Order save(Order order) {
        orders.put(order.id(), order);
        return order;
    }

    @Override
    public Order search(String id) {
        return orders.get(id);
    }

    @Override
    public List<Order> searchAll() {
        return new ArrayList<>(orders.values());
    }

}
