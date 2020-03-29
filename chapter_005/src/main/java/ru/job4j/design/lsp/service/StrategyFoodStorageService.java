package ru.job4j.design.lsp.service;

import ru.job4j.design.lsp.food.Food;
import ru.job4j.design.lsp.storage.FoodStorage;
import ru.job4j.design.lsp.strategy.StorageStrategy;

import java.util.List;

public class StrategyFoodStorageService implements FoodStorageService {
    private final StorageStrategy storageStrategy;
    private final FoodStorage storage;

    public StrategyFoodStorageService(StorageStrategy storageStrategy, FoodStorage storage) {
        this.storageStrategy = storageStrategy;
        this.storage = storage;
    }

    @Override
    public boolean add(Food food) {
        return storageStrategy.add(food, storage);
    }

    @Override
    public List<Food> findAll() {
        return storage.findAll();
    }
}
