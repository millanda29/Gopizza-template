package com.gopizza.shared.application;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopizza.shared.domain.order.OrderCompletedEvent;
import com.gopizza.shared.domain.order.OrderCreatedEvent;
import com.gopizza.shared.domain.pizza.PizzaCreatedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, String> kafka;
    private final ObjectMapper mapper;

    public void publish(OrderCreatedEvent event) {
        try {
            var json = mapper.writeValueAsString(event);
            kafka.send("order.created", json);
        } catch (JsonProcessingException e) {
            System.out.println("error processing event: " + e.getMessage());
        }
    }

    public void publish(PizzaCreatedEvent event) {
        try {
            var json = mapper.writeValueAsString(event);
            kafka.send("pizza.created", json);
        } catch (JsonProcessingException e) {
            System.out.println("error processing event: " + e.getMessage());
        }
    }

    public void publish(OrderCompletedEvent event) {
        try {
            var json = mapper.writeValueAsString(event);
            kafka.send("order.completed", json);
        } catch (JsonProcessingException e) {
            System.out.println("error processing event: " + e.getMessage());
        }
    }

}