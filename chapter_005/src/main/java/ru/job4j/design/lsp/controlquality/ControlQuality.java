package ru.job4j.design.lsp.controlquality;

import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.service.FoodStorageService;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<FoodStorageService> storages = new ArrayList<>();

    public void addStorageService(FoodStorageService service) {
        storages.add(service);
    }

    public void distribute(Food food) {
        for (var storage : storages) {
            if (storage.check(food)) {
                storage.add(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (var storage : storages) {
            foods.addAll(storage.findAll());
            storage.empty();
        }
        foods.forEach(this::distribute);
    }
}
