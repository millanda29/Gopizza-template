package com.gopizza.ordering.order.infrastructure.subscriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopizza.ordering.order.application.complete.CompleteOrderPizzaUseCase;
import com.gopizza.shared.domain.pizza.PizzaCreatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CompleteOrderPizzaOnPizzaCreated {

    private final CompleteOrderPizzaUseCase completeOrderPizzaUseCase;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "pizza.created")
    public void handle(String message) {
        try {
            PizzaCreatedEvent event = objectMapper.readValue(message, PizzaCreatedEvent.class);
            
            System.out.println("Recibe PizzaCreatedEvent - Pizza: " + event.id() + ", Orden: " + event.orderId());
            
            completeOrderPizzaUseCase.execute(event.orderId(), event.id());
            
        } catch (JsonProcessingException e) {
            System.err.println("Error Procesando PizzaCreatedEvent: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error Completando orden pizza: " + e.getMessage());
        }
    }
}