package ru.job4j.design.lsp.strategy;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.storage.ListFoodStorage;
import ru.job4j.design.lsp.controlquality.strategy.StorageStrategy;
import ru.job4j.design.lsp.controlquality.strategy.WarehouseStorageStrategy;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class WarehouseStorageStrategyTest {
    private ListFoodStorage storage;
    private StorageStrategy strategy;

    @Before
    public void setUp() {
        storage = new ListFoodStorage();
        strategy = new WarehouseStorageStrategy();
    }

    @Test
    public void whenExpireLess25ThanFoodShouldBeAddedToStorage() {
        strategy.add(
                new Food("", LocalDate.now().minusDays(1), LocalDate.now().plusDays(3), 0),
                storage
        );
        assertThat(storage.findAll(), hasSize(1));
    }

    @Test
    public void whenExpiredGrater25ThanProductShouldNotBeAddedToStorage() {
        strategy.add(
                new Food("", LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), 0),
                storage
        );
        assertThat(storage.findAll(), hasSize(0));
    }
}