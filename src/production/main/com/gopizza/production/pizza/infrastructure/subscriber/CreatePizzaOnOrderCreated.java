package com.gopizza.production.pizza.infrastructure.subscriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopizza.production.pizza.application.create.CreatePizzaUseCase;
import com.gopizza.shared.domain.order.OrderCreatedEvent;
import com.gopizza.shared.domain.order.OrderPizza;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePizzaOnOrderCreated {

    private final CreatePizzaUseCase createPizzaUseCase;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "order.created")
    public void handle(String message) {
        try {
            // Deserializar
            OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);
            
            System.out.println("Recibe OrderCreatedEvent para orden: " + event.id());
            

            for (OrderPizza orderPizza : event.pizzas()) {

                String pizzaId = orderPizza.id() != null ? orderPizza.id() : NanoIdUtils.randomNanoId();
                
                System.out.println("Creando pizza: " + pizzaId + " para orden: " + event.id());

                processPizzaAsync(pizzaId, event.id(), orderPizza);
            }
            
        } catch (JsonProcessingException e) {
            System.err.println("Error Procesando OrderCreatedEvent: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error Creando pizzas: " + e.getMessage());
        }
    }

    @Async
    protected void processPizzaAsync(String pizzaId, String orderId, OrderPizza orderPizza) {
        try {
            // Ejecutar la creación de pizza (incluye simulación de tiempo)
            createPizzaUseCase.execute(
                pizzaId,
                orderId,
                orderPizza.type(),
                orderPizza.size()
            );
        } catch (Exception e) {
            System.err.println("Error Procesando pizza " + pizzaId + ": " + e.getMessage());
        }
    }
}