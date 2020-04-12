package ru.job4j.design.lsp.parkinglot.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.service.impl.FreeSlotServiceImpl;
import ru.job4j.design.lsp.parkinglot.slot.CarSlot;
import ru.job4j.design.lsp.parkinglot.slot.TruckSlot;
import ru.job4j.design.lsp.parkinglot.storage.impl.MemorySlotStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;
import ru.job4j.design.lsp.parkinglot.vehicle.Truck;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FreeSlotServiceImplTest {

    private FreeSlotService service;

    @Before
    public void setUp() {
        service = new FreeSlotServiceImpl(new MemorySlotStorage());
    }

    @Test
    public void whenFreeSlotsForVehicleTypeAreAvailableThanGetFreeSlotsShouldReturnCorrectAmount() {
        var carSlot = new CarSlot();
        var truckSlot = new TruckSlot();
        service.add(List.of(carSlot, truckSlot));

        var slots = service.getFreeSlots(new Car());

        assertThat(slots, is(List.of(carSlot)));

        slots = service.getFreeSlots(new Truck(2));

        assertThat(slots, is(List.of(truckSlot)));
    }

    @Test
    public void whenNoFreeSlotsForTruckThanGetFreeSlotsShouldReturnCorrectAmountOfCarSlots() {
        var first = new CarSlot();
        var second = new CarSlot();
        service.add(List.of(first, second));
        var slots = service.getFreeSlots(new Truck(2));

        assertThat(slots, is(List.of(first, second)));
    }

    @Test
    public void whenNoFreeSlotsForTruckAndNotEnoughCarSpotsThanGetFreeSlotsShouldReturnEmptyList() {
        service.add(List.of(new CarSlot()));
        var slots = service.getFreeSlots(new Truck(2));

        assertThat(slots, is(List.of()));
    }

    @Test
    public void whenNoFreeSlotsForVehicleThanEmptyListShouldBeReturned() {
        var slots = service.getFreeSlots(new Car());

        assertThat(slots, hasSize(0));
    }

    @Test
    public void removeShouldRemoveGivenListOfSpotsFormService() {
        var slot = new CarSlot();
        service.add(List.of(slot));
        var slots = service.getFreeSlots(new Car());

        assertThat(slots, is(List.of(slot)));

        service.remove(slots);
        slots = service.getFreeSlots(new Car());

        assertThat(slots, hasSize(0));
    }

    @Test
    public void whenUpdateServiceByUnparkedCarThanSlotsShouldBeRemovedFromService() {
        service.add(List.of(new CarSlot()));
        var car = new Car();
        var slots = service.getFreeSlots(car);
        car.setSlots(slots);
        service.update(car);
        slots = service.getFreeSlots(new Car());

        assertThat(slots, hasSize(0));
    }

    @Test
    public void whenUpdateServiceByParkedCarThanSlotsShouldBeAddedToService() {
        var car = new Car();
        car.setSlots(List.of(new CarSlot()));
        service.update(car);
        var slots = service.getFreeSlots(new Car());

        assertThat(slots, hasSize(1));
    }
}