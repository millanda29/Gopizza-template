package com.gopizza.shared.domain.pizza;

import java.util.List;
import java.util.Random;

public class PizzaIngredientsMapper {

    private static final Random random = new Random();

    public static List<PizzaIngredient> generate(PizzaType type) {
        return switch (type) {
            case MARGHERITA -> margherita();
            case PEPPERONI -> pepperoni();
            case HAWAIIAN -> hawaiian();
            case VEGGIE -> veggie();
            case CHEESE -> cheese();
        };
    }

    private static List<PizzaIngredient> margherita() {
        return List.of(
            ing(PizzaIngredientType.DOUGH, 1),
            ing(PizzaIngredientType.TOMATO, rand(60, 90)),
            ing(PizzaIngredientType.CHEESE, rand(80, 120))
        );
    }

    private static List<PizzaIngredient> pepperoni() {
        return List.of(
            ing(PizzaIngredientType.DOUGH, 1),
            ing(PizzaIngredientType.TOMATO, rand(60, 90)),
            ing(PizzaIngredientType.CHEESE, rand(80, 120)),
            ing(PizzaIngredientType.PEPPERONI, rand(10, 20))
        );
    }

    private static List<PizzaIngredient> hawaiian() {
        return List.of(
            ing(PizzaIngredientType.DOUGH, 1),
            ing(PizzaIngredientType.TOMATO, rand(60, 90)),
            ing(PizzaIngredientType.CHEESE, rand(80, 120)),
            ing(PizzaIngredientType.HAM, rand(15, 25)),
            ing(PizzaIngredientType.PINEAPPLE, rand(20, 30))
        );
    }

    private static List<PizzaIngredient> veggie() {
        return List.of(
            ing(PizzaIngredientType.DOUGH, 1),
            ing(PizzaIngredientType.TOMATO, rand(60, 90)),
            ing(PizzaIngredientType.CHEESE, rand(80, 120)),
            ing(PizzaIngredientType.MUSHROOM, rand(10, 20)),
            ing(PizzaIngredientType.ONION, rand(10, 20)),
            ing(PizzaIngredientType.OLIVE, rand(5, 10))
        );
    }

    private static List<PizzaIngredient> cheese() {
        return List.of(
            ing(PizzaIngredientType.DOUGH, 1),
            ing(PizzaIngredientType.CHEESE, rand(100, 150))
        );
    }

    private static PizzaIngredient ing(PizzaIngredientType type, int quantity) {
        return new PizzaIngredient(type, quantity);
    }

    private static int rand(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}