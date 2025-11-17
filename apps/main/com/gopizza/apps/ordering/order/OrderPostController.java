package com.gopizza.apps.ordering.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gopizza.ordering.order.application.create.CreateOrderUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderPostController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateOrderRequest request) {

        createOrderUseCase.execute(request.id(), request.pizzas());
        
        return ResponseEntity.noContent().build();
    }

}
