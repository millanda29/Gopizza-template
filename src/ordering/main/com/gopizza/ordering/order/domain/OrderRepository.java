package com.gopizza.ordering.order.domain;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);

    Order search(String id);

    List<Order> searchAll();
}
