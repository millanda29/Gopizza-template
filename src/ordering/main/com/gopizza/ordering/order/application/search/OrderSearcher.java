package com.gopizza.ordering.order.application.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gopizza.ordering.order.domain.Order;
import com.gopizza.ordering.order.domain.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderSearcher {

    private final OrderRepository repository;

    public Order search(String id) {
        return repository.search(id);
    }

    public List<Order> searchAll() {
        return repository.searchAll();
    }

}
