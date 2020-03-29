package ru.job4j.design.lsp.controlquality.service;

import ru.job4j.design.lsp.controlquality.food.Food;

import java.util.List;

public interface FoodStorageService {
    boolean add(Food food);

    List<Food> findAll();
}
