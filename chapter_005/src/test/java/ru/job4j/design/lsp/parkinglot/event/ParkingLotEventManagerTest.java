package ru.job4j.design.lsp.parkinglot.event;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.event.impl.ParkingLotEventManager;
import ru.job4j.design.lsp.parkinglot.service.FreeSlotService;
import ru.job4j.design.lsp.parkinglot.service.impl.FreeSlotServiceImpl;
import ru.job4j.design.lsp.parkinglot.slot.CarSlot;
import ru.job4j.design.lsp.parkinglot.storage.impl.MemorySlotStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class ParkingLotEventManagerTest {

    private EventManager manager;
    private FreeSlotService service;

    @Before
    public void setUp() {
        manager = new ParkingLotEventManager();
        service = new FreeSlotServiceImpl(new MemorySlotStorage());
        manager.subscribe(service);
    }

    @Test
    public void whenOneFreeSlotAvailableThanAfterNotifyToParkVehicleFreeSlotServiceShouldNotHaveFreeSlots() {
        var car = new Car();
        service.add(List.of(new CarSlot()));
        car.setSlots(service.getFreeSlots(car));
        manager.notify(car);
        var slots = service.getFreeSlots(car);

        assertThat(slots, hasSize(0));
    }
}