package com.gopizza.ordering.order.application.complete;

import org.springframework.stereotype.Service;

import com.gopizza.ordering.order.domain.Order;
import com.gopizza.ordering.order.domain.OrderRepository;
import com.gopizza.shared.application.RabbitPublisher;
import com.gopizza.shared.domain.order.OrderCompletedEvent;
import com.gopizza.shared.domain.order.OrderStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompleteOrderPizzaUseCase {

    private final OrderRepository orderRepository;
    private final RabbitPublisher rabbitPublisher;

    public Order execute(String orderId, String pizzaId) {
        Order order = orderRepository.search(orderId);
        
        if (order == null) {
            throw new IllegalArgumentException("Orden no encontrado con el ID: " + orderId);
        }
        
        System.out.println("Completando pizza " + pizzaId + " para orden " + orderId);

        order.completePizza(pizzaId);

        Order savedOrder = orderRepository.save(order);

        if (savedOrder.status() == OrderStatus.COMPLETED) {
            System.out.println("Orden " + orderId + " esta ahora completa, publicando evento.");
            OrderCompletedEvent event = new OrderCompletedEvent(
                savedOrder.id(),
                savedOrder.completedAt()
            );
            rabbitPublisher.publish(event);
        }
        
        return savedOrder;
    }
}