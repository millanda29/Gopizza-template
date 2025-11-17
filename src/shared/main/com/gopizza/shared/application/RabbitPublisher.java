package com.gopizza.shared.application;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopizza.shared.domain.order.OrderCompletedEvent;
import com.gopizza.shared.domain.order.OrderCreatedEvent;
import com.gopizza.shared.domain.pizza.PizzaCreatedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitPublisher {

    private final RabbitTemplate template;
    private final ObjectMapper mapper;

    public void publish(OrderCreatedEvent event) {
        try {
            var json = mapper.writeValueAsString(event);
            template.convertAndSend("order.created", json);
        } catch (JsonProcessingException e) {
            System.err.println("Error Publicando OrderCreatedEvent: " + e.getMessage());
        }
    }

    public void publish(PizzaCreatedEvent event) {
        try {
            var json = mapper.writeValueAsString(event);
            template.convertAndSend("pizza.created", json);
        } catch (JsonProcessingException e) {
            System.err.println("Error Publicando PizzaCreatedEvent: " + e.getMessage());
        }
    }

    public void publish(OrderCompletedEvent event) {
        try {
            var json = mapper.writeValueAsString(event);
            template.convertAndSend("order.completed", json);
        } catch (JsonProcessingException e) {
            System.err.println("Error Publicando OrderCompletedEvent: " + e.getMessage());
        }
    }


}