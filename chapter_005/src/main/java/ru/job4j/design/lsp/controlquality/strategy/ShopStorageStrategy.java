package ru.job4j.design.lsp.controlquality.strategy;

import ru.job4j.design.lsp.controlquality.date.FoodExpirePercentCalculator;
import ru.job4j.design.lsp.controlquality.food.DiscountFoodDecorator;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.storage.FoodStorage;

import java.util.function.Predicate;

public class ShopStorageStrategy implements StorageStrategy {
    private final Predicate<Food> predicate;

    public ShopStorageStrategy(Predicate<Food> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean add(Food food, FoodStorage storage) {
        var percents = calculateExpirePercentage(food);
        if (percents >= 75) {
            food = new DiscountFoodDecorator(food, 5);
        }
        return storage.add(food);
    }

    @Override
    public boolean check(Food food) {
        return predicate.test(food);
    }

    private int calculateExpirePercentage(Food food) {
        return new FoodExpirePercentCalculator(food.getCreateDate(), food.getExpireDate())
                .calculate();
    }
}
