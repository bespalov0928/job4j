package ru.job4j.design.lsp.storage;

import ru.job4j.design.lsp.food.Food;

import java.util.List;

public interface FoodStorage {
    boolean add(Food food);

    List<Food> findAll();
}
