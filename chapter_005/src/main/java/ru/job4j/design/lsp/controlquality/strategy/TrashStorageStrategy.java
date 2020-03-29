package ru.job4j.design.lsp.controlquality.strategy;

import ru.job4j.design.lsp.controlquality.date.FoodExpirePercentCalculator;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.storage.FoodStorage;

public class TrashStorageStrategy implements StorageStrategy {

    @Override
    public boolean add(Food food, FoodStorage storage) {
        var percents = new FoodExpirePercentCalculator(food.getCreateDate(), food.getExpireDate())
                .calculate();
        var result = false;
        if (percents == 100) {
            result = storage.add(food);
        }
        return result;
    }
}
