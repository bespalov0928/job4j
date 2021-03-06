package ru.job4j.design.lsp.controlquality.storage;

import ru.job4j.design.lsp.controlquality.food.Food;

import java.util.List;

public interface FoodStorage {
    boolean add(Food food);

    List<Food> findAll();

    void empty();
}
