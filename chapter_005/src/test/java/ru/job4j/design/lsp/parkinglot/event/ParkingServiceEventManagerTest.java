package ru.job4j.design.lsp.parkinglot.event;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.event.impl.ParkingServiceEventManager;
import ru.job4j.design.lsp.parkinglot.service.FreeSlotService;
import ru.job4j.design.lsp.parkinglot.service.impl.FreeSlotServiceImpl;
import ru.job4j.design.lsp.parkinglot.slot.CarSlot;
import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class ParkingServiceEventManagerTest {

    private EventManager eventManager;
    private FreeSlotService freeSlotService;

    @Before
    public void setUp() {
        eventManager = new ParkingServiceEventManager();
        freeSlotService = new FreeSlotServiceImpl();
        eventManager.subscribe(freeSlotService);
    }

    @Test
    public void whenOneFreeSlotAvailableThanAfterNotifyToParkVehicleFreeSlotServiceShouldNotHaveFreeSlots() {
        var car = new Car();
        List<Slot> slotList = List.of(new CarSlot());
        freeSlotService.add(slotList);
        slotList = freeSlotService.getFreeSlots(car);
        car.setSlots(slotList);
        eventManager.notify(car);
        slotList = freeSlotService.getFreeSlots(car);
        assertThat(slotList, hasSize(0));
    }
}