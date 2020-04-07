package ru.job4j.design.lsp.controlquality;

import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.service.FoodStorageService;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<FoodStorageService> storageServices = new ArrayList<>();

    public void addStorageService(FoodStorageService service) {
        storageServices.add(service);
    }

    public void distribute(Food food) {
        for (var service : storageServices) {
            if (service.check(food)) {
                service.add(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (var service : storageServices) {
            foods.addAll(service.findAll());
            service.empty();
        }
        foods.forEach(this::distribute);
    }
}
