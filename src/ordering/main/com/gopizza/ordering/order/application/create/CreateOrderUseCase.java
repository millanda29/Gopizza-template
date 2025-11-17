package com.gopizza.ordering.order.application.create;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gopizza.ordering.order.domain.Order;
import com.gopizza.ordering.order.domain.OrderRepository;
import com.gopizza.shared.application.RabbitPublisher;
import com.gopizza.shared.domain.order.OrderCreatedEvent;
import com.gopizza.shared.domain.order.OrderPizza;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final RabbitPublisher rabbitPublisher;

    public Order execute(String orderId, List<OrderPizza> pizzas) {

        Order order = Order.create(orderId, pizzas);
        

        Order savedOrder = orderRepository.save(order);
        
        // Publicar evento OrderCreatedEvent usando RabbitPublisher
        OrderCreatedEvent event = OrderCreatedEvent.from(savedOrder.id(), savedOrder.items());
        rabbitPublisher.publish(event);
        
        return savedOrder;
    }
}