package com.gopizza.apps.ordering.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderPostController {

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.noContent().build();
    }

}
