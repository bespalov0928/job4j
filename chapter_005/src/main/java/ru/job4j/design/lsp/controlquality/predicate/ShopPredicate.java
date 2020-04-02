package ru.job4j.design.lsp.controlquality.predicate;

import ru.job4j.design.lsp.controlquality.date.FoodExpirePercentCalculator;
import ru.job4j.design.lsp.controlquality.food.Food;

import java.util.function.Predicate;

public class ShopPredicate implements Predicate<Food> {

    @Override
    public boolean test(Food food) {
        var percents = new FoodExpirePercentCalculator(food.getCreateDate(), food.getExpireDate())
                .calculate();
        var result = false;
        if (percents > 25 && percents < 100) {
            result = true;
        }
        return result;
    }
}
