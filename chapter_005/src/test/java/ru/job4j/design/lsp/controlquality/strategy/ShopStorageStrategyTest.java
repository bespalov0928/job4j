package ru.job4j.design.lsp.controlquality.strategy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.predicate.ShopPredicate;
import ru.job4j.design.lsp.controlquality.storage.ListFoodStorage;
import ru.job4j.design.lsp.controlquality.strategy.ShopStorageStrategy;
import ru.job4j.design.lsp.controlquality.strategy.StorageStrategy;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class ShopStorageStrategyTest {
    private ListFoodStorage storage;
    private StorageStrategy strategy;

    @Before
    public void setUp() {
        storage = new ListFoodStorage();
        strategy = new ShopStorageStrategy(new ShopPredicate());
    }

    @Test
    public void whenExpireLess25ThanFoodShouldNotBeAddedToStorage() {
        var food = new Food("", LocalDate.now().minusDays(1), LocalDate.now().plusDays(4), 0);
        add(food);
        assertThat(storage.findAll(), hasSize(0));
    }

    @Test
    public void whenExpiredGrater25ThanFoodShouldBeAddedToStorage() {
        var food = new Food("", LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), 0);
        add(food);
        assertThat(storage.findAll(), hasSize(1));
    }

    @Test
    public void whenExpiredGrater75ThanFoodShouldBeAddedToStorageWithDiscount() {
        var food = new Food("", LocalDate.now().minusDays(3), LocalDate.now().plusDays(1), 10);
        add(food);
        var foods = storage.findAll();
        assertThat(foods, hasSize(1));
        assertNotEquals(food.getPrice(), foods.get(0).getPrice());
    }

    private void add(Food food) {
        if (strategy.check(food)) {
            strategy.add(food, storage);
        }
    }
}