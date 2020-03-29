package ru.job4j.design.lsp.controlquality.strategy;

import ru.job4j.design.lsp.controlquality.date.FoodExpirePercentCalculator;
import ru.job4j.design.lsp.controlquality.food.DiscountFoodDecorator;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.storage.FoodStorage;

public class ShopStorageStrategy implements StorageStrategy {

    @Override
    public boolean add(Food food, FoodStorage storage) {
        var percents = new FoodExpirePercentCalculator(food.getCreateDate(), food.getExpireDate())
                .calculate();
        var result = false;
        if (percents > 25 && percents < 75) {
            result = storage.add(food);
        } else if (percents >= 75) {
            result = storage.add(new DiscountFoodDecorator(food, 5));
        }
        return result;
    }
}
