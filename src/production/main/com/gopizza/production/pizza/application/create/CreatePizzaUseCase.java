package com.gopizza.production.pizza.application.create;

import org.springframework.stereotype.Service;

import com.gopizza.production.pizza.domain.Pizza;
import com.gopizza.production.pizza.domain.PizzaRepository;
import com.gopizza.shared.application.RabbitPublisher;
import com.gopizza.shared.domain.pizza.PizzaCreatedEvent;
import com.gopizza.shared.domain.pizza.PizzaSize;
import com.gopizza.shared.domain.pizza.PizzaType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePizzaUseCase {

    private final PizzaRepository pizzaRepository;
    private final RabbitPublisher rabbitPublisher;

    public Pizza execute(String pizzaId, String orderId, PizzaType type, PizzaSize size) {
        // Calcular tiempo de preparación en segundos
        int preparationTimeSeconds = calculatePreparationTimeSeconds(size);
        
        System.out.println("Starting pizza creation - ID: " + pizzaId + ", Order: " + orderId + ", Time: " + preparationTimeSeconds + " seconds");
        
        // Simular el tiempo de preparación real de la pizza en segundos
        simulatePreparationTime(preparationTimeSeconds);
        
        // Después de esperar esos segundos, establecer ese mismo valor como creationTimeMinutes
        // (Según README: el valor simulado en segundos se debe usar como creationTimeMinutes)
        Pizza pizza = Pizza.create(pizzaId, orderId, type, size, preparationTimeSeconds);
        
        // Guardar la pizza creada en el repositorio
        Pizza savedPizza = pizzaRepository.save(pizza);
        
        // Publicar PizzaCreatedEvent al terminar de crear la pizza
        PizzaCreatedEvent event = PizzaCreatedEvent.from(
            savedPizza.id(),
            savedPizza.orderId(),
            savedPizza.type(),
            savedPizza.size(),
            savedPizza.createdAt()
        );
        rabbitPublisher.publish(event);
        
        System.out.println("Pizza created and event published - ID: " + savedPizza.id());
        
        return savedPizza;
    }


    private int calculatePreparationTimeSeconds(PizzaSize size) {
        return switch (size) {
            case SMALL -> 2;   // 2 segundos
            case MEDIUM -> 4;  // 4 segundos  
            case LARGE -> 6;   // 6 segundos
        };
    }

    private void simulatePreparationTime(int seconds) {
        try {
            // Simular el tiempo de preparación real de la pizza en segundos
            Thread.sleep(seconds * 1000L); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Pizza preparation was interrupted", e);
        }
    }
}