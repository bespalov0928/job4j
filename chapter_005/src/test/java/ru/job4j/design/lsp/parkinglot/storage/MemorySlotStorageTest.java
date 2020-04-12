package ru.job4j.design.lsp.parkinglot.storage;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.slot.CarSlot;
import ru.job4j.design.lsp.parkinglot.storage.impl.MemorySlotStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

public class MemorySlotStorageTest {
    private SlotStorage storage;

    @Before
    public void setUp() {
        storage = new MemorySlotStorage();
    }

    @Test
    public void whenAddSlotThanFindShouldReturnListWithAddedSlot() {
        var slot = new CarSlot();
        storage.add(slot);

        assertThat(storage.findByVehicleType(VehicleType.Car), is(List.of(slot)));
    }

    @Test
    public void whenDeleteAddedSlotThanFindShouldReturnEmptyList() {
        var slot = new CarSlot();
        storage.add(slot);
        storage.remove(slot);

        assertThat(storage.findByVehicleType(VehicleType.Car), hasSize(0));
    }
}