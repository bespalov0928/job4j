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

    private FreeSlotService freeSlotService;

    @Before
    public void setUp() {
        freeSlotService = new FreeSlotServiceImpl();
    }

    @Test
    public void whenFreeSlotsForVehicleTypeAreAvailableThanGetFreeSlotsShouldReturnCorrectAmount() {
        var carSlot = new CarSlot();
        var truckSlot = new TruckSlot();
        freeSlotService.add(List.of(carSlot, truckSlot));
        var freeSlots = freeSlotService.getFreeSlots(new Car());
        assertThat(freeSlots, is(List.of(carSlot)));
        freeSlots = freeSlotService.getFreeSlots(new Truck(2));
        assertThat(freeSlots, is(List.of(truckSlot)));
    }

    @Test
    public void whenNoFreeSlotsForTruckThanGetFreeSlotsShouldReturnCorrectAmountOfCarSlots() {
        var firstCarSlot = new CarSlot();
        var secondCarSlot = new CarSlot();
        freeSlotService.add(List.of(firstCarSlot, secondCarSlot));
        var freeSlots = freeSlotService.getFreeSlots(new Truck(2));
        assertThat(freeSlots, is(List.of(firstCarSlot, secondCarSlot)));
    }

    @Test
    public void whenNoFreeSlotsForVehicleThanEmptyListShouldBeReturned() {
        var freeSlots = freeSlotService.getFreeSlots(new Car());
        assertThat(freeSlots, hasSize(0));
    }

    @Test
    public void removeShouldRemoveGivenListOfSpotsFormService() {
        var firstCarSlot = new CarSlot();
        freeSlotService.add(List.of(firstCarSlot));
        var freeSlots = freeSlotService.getFreeSlots(new Car());
        assertThat(freeSlots, is(List.of(firstCarSlot)));
        freeSlotService.remove(freeSlots);
        freeSlots = freeSlotService.getFreeSlots(new Car());
        assertThat(freeSlots, hasSize(0));
    }

    @Test
    public void whenUpdateServiceByUnparkedCarThanSlotsShouldBeRemovedFromService() {
        var firstCarSlot = new CarSlot();
        freeSlotService.add(List.of(firstCarSlot));
        var car = new Car();
        var freeSlots = freeSlotService.getFreeSlots(car);
        car.setSlots(freeSlots);
        freeSlotService.update(car);
        freeSlots = freeSlotService.getFreeSlots(new Car());
        assertThat(freeSlots, hasSize(0));
    }

    @Test
    public void whenUpdateServiceByParkedCarThanSlotsShouldBeAddedToService() {
        var car = new Car();
        car.setSlots(List.of(new CarSlot()));
        freeSlotService.update(car);
        var freeSlots = freeSlotService.getFreeSlots(new Car());
        assertThat(freeSlots, hasSize(1));
    }
}