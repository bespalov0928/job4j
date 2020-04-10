package ru.job4j.design.lsp.controlquality.service;

import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.storage.FoodStorage;
import ru.job4j.design.lsp.controlquality.strategy.StorageStrategy;

import java.util.List;

public class StrategyFoodStorageService implements FoodStorageService {
    private final StorageStrategy strategy;
    private final FoodStorage storage;

    public StrategyFoodStorageService(StorageStrategy strategy, FoodStorage storage) {
        this.strategy = strategy;
        this.storage = storage;
    }

    @Override
    public boolean check(Food food) {
        return strategy.check(food);
    }

    @Override
    public boolean add(Food food) {
        return strategy.add(food, storage);
    }

    @Override
    public List<Food> findAll() {
        return storage.findAll();
    }

    @Override
    public void empty() {
        storage.empty();
    }
}
