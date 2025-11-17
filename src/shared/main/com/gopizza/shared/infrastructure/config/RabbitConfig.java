package com.gopizza.shared.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue("order.created", true); // durable = true
    }

    @Bean
    public Queue pizzaCreatedQueue() {
        return new Queue("pizza.created", true); // durable = true
    }

    @Bean
    public Queue orderCompletedQueue() {
        return new Queue("order.completed", true); // durable = true
    }
}