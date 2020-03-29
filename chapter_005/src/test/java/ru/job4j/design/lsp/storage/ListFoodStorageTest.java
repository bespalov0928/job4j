package ru.job4j.design.lsp.storage;

import org.junit.Test;
import ru.job4j.design.lsp.controlquality.food.Food;
import ru.job4j.design.lsp.controlquality.storage.ListFoodStorage;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class ListFoodStorageTest {

    @Test
    public void whenAddFoodThatFindAllShouldReturnCorrectTotalAddedFoods() {
        var foodStorage = new ListFoodStorage();
        foodStorage.add(new Food("Milk", LocalDate.now(), LocalDate.now(), 1.25));
        assertThat(foodStorage.findAll(), hasSize(1));
    }
}