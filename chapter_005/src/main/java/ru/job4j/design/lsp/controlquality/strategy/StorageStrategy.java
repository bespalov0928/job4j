package ru.job4j.design.lsp.controlquality.strategy;

import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.storage.FoodStorage;

public interface StorageStrategy {
    boolean add(Food food, FoodStorage storage);

    boolean check(Food food);
}
