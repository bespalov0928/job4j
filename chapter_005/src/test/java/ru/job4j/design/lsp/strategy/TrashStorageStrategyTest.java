package ru.job4j.design.lsp.strategy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.food.Food;
import ru.job4j.design.lsp.storage.ListFoodStorage;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class TrashStorageStrategyTest {
    private ListFoodStorage storage;
    private StorageStrategy strategy;

    @Before
    public void setUp() {
        storage = new ListFoodStorage();
        strategy = new TrashStorageStrategy();
    }

    @Test
    public void whenAddExpiredFoodThanFoodShouldBeAddedToStorage() {
        strategy.add(
                new Food("", LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), 0),
                storage
        );
        assertThat(storage.findAll(), hasSize(1));
    }

    @Test
    public void whenAddNotExpiredFoodThanProductShouldNotBeAddedToStorage() {
        strategy.add(
                new Food("", LocalDate.now().minusDays(2), LocalDate.now().plusDays(10), 0),
                storage
        );
        assertThat(storage.findAll(), hasSize(0));
    }
}