package com.gopizza.apps.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.gopizza.apps.ordering.order.CreateOrderRequest;
import com.gopizza.shared.domain.order.OrderPizza;
import com.gopizza.shared.domain.pizza.PizzaSize;
import com.gopizza.shared.domain.pizza.PizzaType;

@Component
public class FakeOrderGenerator {

    private static final String ORDER_URL = "http://localhost:%s/api/orders";
    private static final long INTERVAL = 14000L;

    @Value("${server.port}")
    private int port;

    private final RestTemplate template = new RestTemplate();
    private final Random random = new Random();

    @Scheduled(fixedRate = INTERVAL)
    public void generateOrder() {
        CreateOrderRequest request = randomOrder();

        try {
            template.postForEntity(
                String.format(ORDER_URL, port),
                request,
                Void.class
            );
        } catch (Exception e) {
            System.err.println("error creando pedido: " + e.getMessage());
        }
    }

    private CreateOrderRequest randomOrder() {
        return new CreateOrderRequest(
            NanoIdUtils.randomNanoId(),
            randomPizzas()
        );
    }

    private List<OrderPizza> randomPizzas() {
        int count = random.nextInt(6) + 1;
        List<OrderPizza> pizzas = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            pizzas.add(new OrderPizza(
                NanoIdUtils.randomNanoId(),
                randomType(),
                randomSize(),
                false,
                random.nextInt(11) + 5
            ));
        }

        return pizzas;
    }

    private PizzaType randomType() {
        PizzaType[] values = PizzaType.values();
        return values[random.nextInt(values.length)];
    }

    private PizzaSize randomSize() {
        PizzaSize[] values = PizzaSize.values();
        return values[random.nextInt(values.length)];
    }

}