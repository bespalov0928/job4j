package ru.job4j.design.lsp.strategy;

import ru.job4j.design.lsp.date.FoodExpirePercentCalculator;
import ru.job4j.design.lsp.food.Food;
import ru.job4j.design.lsp.storage.FoodStorage;

public class WarehouseStorageStrategy implements StorageStrategy {

    @Override
    public boolean add(Food food, FoodStorage storage) {
        var percents = new FoodExpirePercentCalculator(food.getCreateDate(), food.getExpireDate())
                .calculate();
        var result = false;
        if (percents <= 25) {
            result = storage.add(food);
        }
        return result;
    }
}
