package ru.job4j.design.lsp.controlquality.strategy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.predicate.TrashPredicate;
import ru.job4j.design.lsp.controlquality.storage.ListFoodStorage;
import ru.job4j.design.lsp.controlquality.strategy.StorageStrategy;
import ru.job4j.design.lsp.controlquality.strategy.TrashStorageStrategy;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class TrashStorageStrategyTest {
    private ListFoodStorage storage;
    private StorageStrategy strategy;

    @Before
    public void setUp() {
        storage = new ListFoodStorage();
        strategy = new TrashStorageStrategy(new TrashPredicate());
    }

    @Test
    public void whenAddExpiredFoodThanFoodShouldBeAddedToStorage() {
        var food = new Food("", LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), 0);
        if (strategy.check(food)) {
            strategy.add(food, storage);
        }
        assertThat(storage.findAll(), hasSize(1));
    }

    @Test
    public void whenAddNotExpiredFoodThanProductShouldNotBeAddedToStorage() {
        var food = new Food("", LocalDate.now().minusDays(2), LocalDate.now().plusDays(10), 0);
        if (strategy.check(food)) {
            strategy.add(food, storage);
        }
        assertThat(storage.findAll(), hasSize(0));
    }
}