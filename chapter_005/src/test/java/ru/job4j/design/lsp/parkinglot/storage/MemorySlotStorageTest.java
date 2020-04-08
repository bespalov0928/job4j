package ru.job4j.design.lsp.parkinglot.storage;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.slot.CarSlot;
import ru.job4j.design.lsp.parkinglot.storage.impl.MemorySlotStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

@Ignore
public class MemorySlotStorageTest {
    private SlotStorage slotStorage;

    @Before
    public void setUp() {
        slotStorage = new MemorySlotStorage();
    }

    @Test
    public void whenAddSlotThanFindShouldReturnListWithAddedSlot() {
        var slot = new CarSlot();
        slotStorage.add(slot);
        assertThat(slotStorage.findByVehicleType(VehicleType.Car), is(List.of(slot)));
    }

    @Test
    public void whenDeleteAddedSlotThanFindShouldReturnEmptyList() {
        var slot = new CarSlot();
        slotStorage.add(slot);
        slotStorage.remove(slot);
        assertThat(slotStorage.findByVehicleType(VehicleType.Car), hasSize(0));
    }
}