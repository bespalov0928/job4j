package ru.job4j.design.lsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.controlquality.ControlQuality;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.predicate.ShopPredicate;
import ru.job4j.design.lsp.controlquality.predicate.TrashPredicate;
import ru.job4j.design.lsp.controlquality.predicate.WarehousePredicate;
import ru.job4j.design.lsp.controlquality.service.StrategyFoodStorageService;
import ru.job4j.design.lsp.controlquality.storage.ListFoodStorage;
import ru.job4j.design.lsp.controlquality.strategy.ShopStorageStrategy;
import ru.job4j.design.lsp.controlquality.strategy.TrashStorageStrategy;
import ru.job4j.design.lsp.controlquality.strategy.WarehouseStorageStrategy;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {
    private ControlQuality controlQuality;

    @Before
    public void setUp() {
        controlQuality = new ControlQuality();
    }

    @Test
    public void whenDistributeFoodThanServiceShouldReturnAmountOfStoredFood() {
        var service = new StrategyFoodStorageService(new TrashStorageStrategy(new TrashPredicate()), new ListFoodStorage());

        controlQuality.addStorageService(service);
        controlQuality.distribute(
                new Food("", LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), 0)
        );

        assertThat(service.findAll(), hasSize(1));
    }

    @Test
    public void whenDistributeFoodThanFoodShouldBeDistributedToCorrectStorage() {
        var trashService = new StrategyFoodStorageService(
                new TrashStorageStrategy(new TrashPredicate()),
                new ListFoodStorage()
        );
        var warehouseService = new StrategyFoodStorageService(
                new WarehouseStorageStrategy(new WarehousePredicate()),
                new ListFoodStorage()
        );
        var shopService = new StrategyFoodStorageService(
                new ShopStorageStrategy(new ShopPredicate()),
                new ListFoodStorage()
        );

        controlQuality.addStorageService(trashService);
        controlQuality.addStorageService(warehouseService);
        controlQuality.addStorageService(shopService);
        controlQuality.distribute(
                new Food("", LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), 0)
        );
        controlQuality.distribute(
                new Food("", LocalDate.now().minusDays(1), LocalDate.now().plusDays(3), 0)
        );
        controlQuality.distribute(
                new Food("", LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), 0)
        );

        assertThat(trashService.findAll(), hasSize(1));
        assertThat(warehouseService.findAll(), hasSize(1));
        assertThat(shopService.findAll(), hasSize(1));
    }

    @Test
    public void whenResortFoodThanFoodShouldBeDistributedToCorrectStorage() {
        var trashService = new StrategyFoodStorageService(
                new TrashStorageStrategy(new TrashPredicate()),
                new ListFoodStorage()
        );
        var warehouseService = new StrategyFoodStorageService(
                new WarehouseStorageStrategy(new WarehousePredicate()),
                new ListFoodStorage()
        );
        var shopService = new StrategyFoodStorageService(
                new ShopStorageStrategy(new ShopPredicate()),
                new ListFoodStorage()
        );

        controlQuality.addStorageService(trashService);
        controlQuality.addStorageService(warehouseService);
        controlQuality.addStorageService(shopService);
        controlQuality.distribute(
                new Food("", LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), 0)
        );
        controlQuality.distribute(
                new Food("", LocalDate.now().minusDays(1), LocalDate.now().plusDays(3), 0)
        );
        controlQuality.distribute(
                new Food("", LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), 0)
        );

        assertThat(trashService.findAll(), hasSize(1));
        assertThat(warehouseService.findAll(), hasSize(1));
        assertThat(shopService.findAll(), hasSize(1));

        controlQuality.resort();

        assertThat(trashService.findAll(), hasSize(1));
        assertThat(warehouseService.findAll(), hasSize(1));
        assertThat(shopService.findAll(), hasSize(1));
    }
}
