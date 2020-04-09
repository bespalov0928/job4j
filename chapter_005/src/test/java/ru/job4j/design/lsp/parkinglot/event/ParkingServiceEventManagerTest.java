package ru.job4j.design.lsp.parkinglot.event;

import org.junit.Before;
import org.junit.Ignore;
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

@Ignore
public class ParkingServiceEventManagerTest {

    private EventManager eventManager;
    private FreeSlotService slotService;

    @Before
    public void setUp() {
        eventManager = new ParkingServiceEventManager();
        slotService = new FreeSlotServiceImpl();
        eventManager.subscribe(slotService);
    }

    @Test
    public void whenOneFreeSlotAvailableThanAfterNotifyToParkVehicleFreeSlotServiceShouldNotHaveFreeSlots() {
        var car = new Car();
        slotService.add(List.of(new CarSlot()));
        car.setSlots(slotService.getFreeSlots(car));
        eventManager.notify(car);
        var slots = slotService.getFreeSlots(car);

        assertThat(slots, hasSize(0));
    }
}