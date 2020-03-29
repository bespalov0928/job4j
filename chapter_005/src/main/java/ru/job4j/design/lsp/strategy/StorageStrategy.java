package ru.job4j.design.lsp.strategy;

import ru.job4j.design.lsp.food.Food;
import ru.job4j.design.lsp.storage.FoodStorage;

public interface StorageStrategy {
    boolean add(Food food, FoodStorage storage);
}
