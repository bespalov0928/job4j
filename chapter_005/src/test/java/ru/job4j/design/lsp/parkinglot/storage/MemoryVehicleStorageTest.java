package ru.job4j.design.lsp.parkinglot.storage;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.storage.impl.MemoryVehicleStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemoryVehicleStorageTest {
    private VehicleStorage storage;

    @Before
    public void setUp() {
        storage = new MemoryVehicleStorage();
    }

    @Test
    public void whenAddVehicleThanCountShouldReturnOne() {
        assertTrue(storage.add(new Car()));
        assertThat(storage.count(), is(1));
    }

    @Test
    public void whenAddSameVehicleThanAddShouldReturnFalseAndCountShouldReturnOne() {
        var car = new Car();
        storage.add(car);

        assertFalse(storage.add(car));
        assertThat(storage.count(), is(1));
    }

    @Test
    public void whenRemoveVehicleThanCountShouldReturnZero() {
        var car = new Car();
        storage.add(car);
        storage.remove(car);

        assertThat(storage.count(), is(0));
    }
}