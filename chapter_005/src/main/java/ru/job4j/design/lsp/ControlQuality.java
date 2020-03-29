package ru.job4j.design.lsp;

import ru.job4j.design.lsp.food.Food;
import ru.job4j.design.lsp.service.FoodStorageService;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<FoodStorageService> storageServices = new ArrayList<>();

    public void addStorageService(FoodStorageService service) {
        storageServices.add(service);
    }

    public void distribute(Food food) {
        for (var service : storageServices) {
            if (service.add(food)) {
                break;
            }
        }
    }
}
