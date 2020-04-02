package ru.job4j.design.lsp.service;

import org.junit.Test;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.predicate.TrashPredicate;
import ru.job4j.design.lsp.controlquality.service.FoodStorageService;
import ru.job4j.design.lsp.controlquality.service.StrategyFoodStorageService;
import ru.job4j.design.lsp.controlquality.storage.FoodStorage;
import ru.job4j.design.lsp.controlquality.storage.ListFoodStorage;
import ru.job4j.design.lsp.controlquality.strategy.TrashStorageStrategy;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class StrategyFoodStorageServiceTest {

    @Test
    public void whenServiceAddExpiredFoodThanFoodShouldBeAddedToStorage() {
        FoodStorage storage = new ListFoodStorage();
        FoodStorageService trashStorageService = new StrategyFoodStorageService(
                new TrashStorageStrategy(new TrashPredicate()),
                storage
        );
        assertTrue(
                trashStorageService.add(
                        new Food("", LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), 0)
                )
        );
        assertThat(trashStorageService.findAll(), hasSize(1));
    }
}