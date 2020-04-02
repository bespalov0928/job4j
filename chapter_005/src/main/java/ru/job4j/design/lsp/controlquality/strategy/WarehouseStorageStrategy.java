package ru.job4j.design.lsp.controlquality.strategy;

import ru.job4j.design.lsp.controlquality.date.FoodExpirePercentCalculator;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.storage.FoodStorage;

import java.util.function.Predicate;

public class WarehouseStorageStrategy implements StorageStrategy {
    private final Predicate<Food> predicate;

    public WarehouseStorageStrategy(Predicate<Food> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean add(Food food, FoodStorage storage) {
        return storage.add(food);
    }

    @Override
    public boolean check(Food food) {
        return predicate.test(food);
    }
}
