package ru.job4j.design.lsp.service;

import ru.job4j.design.lsp.food.Food;

import java.util.List;

public interface FoodStorageService {
    boolean add(Food food);

    List<Food> findAll();
}
