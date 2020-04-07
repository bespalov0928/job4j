package ru.job4j.design.lsp.controlquality.storage;

import ru.job4j.design.lsp.controlquality.food.Food;

import java.util.ArrayList;
import java.util.List;

public class ListFoodStorage implements FoodStorage {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        return foods.add(food);
    }

    @Override
    public List<Food> findAll() {
        return foods;
    }

    @Override
    public void empty() {
        foods.clear();
    }
}
