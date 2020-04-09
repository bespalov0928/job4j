package ru.job4j.design.lsp.parkinglot.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.service.impl.FreeSlotServiceImpl;
import ru.job4j.design.lsp.parkinglot.slot.CarSlot;
import ru.job4j.design.lsp.parkinglot.slot.TruckSlot;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;
import ru.job4j.design.lsp.parkinglot.vehicle.Truck;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class FreeSlotServiceImplTest {

    private FreeSlotService slotService;

    @Before
    public void setUp() {
        slotService = new FreeSlotServiceImpl();
    }

    @Test
    public void whenFreeSlotsForVehicleTypeAreAvailableThanGetFreeSlotsShouldReturnCorrectAmount() {
        var carSlot = new CarSlot();
        var truckSlot = new TruckSlot();
        slotService.add(List.of(carSlot, truckSlot));

        var slots = slotService.getFreeSlots(new Car());

        assertThat(slots, is(List.of(carSlot)));

        slots = slotService.getFreeSlots(new Truck(2));

        assertThat(slots, is(List.of(truckSlot)));
    }

    @Test
    public void whenNoFreeSlotsForTruckThanGetFreeSlotsShouldReturnCorrectAmountOfCarSlots() {
        var first = new CarSlot();
        var second = new CarSlot();
        slotService.add(List.of(first, second));
        var slots = slotService.getFreeSlots(new Truck(2));

        assertThat(slots, is(List.of(first, second)));
    }

    @Test
    public void whenNoFreeSlotsForVehicleThanEmptyListShouldBeReturned() {
        var slots = slotService.getFreeSlots(new Car());

        assertThat(slots, hasSize(0));
    }

    @Test
    public void removeShouldRemoveGivenListOfSpotsFormService() {
        var slot = new CarSlot();
        slotService.add(List.of(slot));
        var slots = slotService.getFreeSlots(new Car());

        assertThat(slots, is(List.of(slot)));

        slotService.remove(slots);
        slots = slotService.getFreeSlots(new Car());

        assertThat(slots, hasSize(0));
    }

    @Test
    public void whenUpdateServiceByUnparkedCarThanSlotsShouldBeRemovedFromService() {
        slotService.add(List.of(new CarSlot()));
        var car = new Car();
        var slots = slotService.getFreeSlots(car);
        car.setSlots(slots);
        slotService.update(car);
        slots = slotService.getFreeSlots(new Car());

        assertThat(slots, hasSize(0));
    }

    @Test
    public void whenUpdateServiceByParkedCarThanSlotsShouldBeAddedToService() {
        var car = new Car();
        car.setSlots(List.of(new CarSlot()));
        slotService.update(car);
        var slots = slotService.getFreeSlots(new Car());

        assertThat(slots, hasSize(1));
    }
}