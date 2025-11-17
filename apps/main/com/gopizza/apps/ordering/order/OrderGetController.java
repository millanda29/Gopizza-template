package com.gopizza.apps.ordering.order;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopizza.ordering.order.application.search.OrderResponse;
import com.gopizza.ordering.order.application.search.OrderSearcher;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderGetController {

    private final OrderSearcher searcher;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> search(@PathVariable String id) {
        var order = searcher.search(id);
        if (order == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> searchAll() {
        var orders = searcher.searchAll();
        return ResponseEntity.ok(OrderResponse.from(orders));
    }

}
