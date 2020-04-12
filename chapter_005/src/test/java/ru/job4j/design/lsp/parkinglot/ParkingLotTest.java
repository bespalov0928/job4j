package ru.job4j.design.lsp.parkinglot;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.event.impl.ParkingLotEventManager;
import ru.job4j.design.lsp.parkinglot.exception.NoEmptySlotsException;
import ru.job4j.design.lsp.parkinglot.exception.VehicleNotParkedException;
import ru.job4j.design.lsp.parkinglot.exception.VehicleParkedException;
import ru.job4j.design.lsp.parkinglot.service.impl.FreeSlotServiceImpl;
import ru.job4j.design.lsp.parkinglot.service.impl.ParkingVehicleServiceImpl;
import ru.job4j.design.lsp.parkinglot.storage.impl.MemorySlotStorage;
import ru.job4j.design.lsp.parkinglot.storage.impl.MemoryVehicleStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;
import ru.job4j.design.lsp.parkinglot.vehicle.Truck;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {
    private ParkingLot parkingLot;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot(
                new FreeSlotServiceImpl(new MemorySlotStorage()),
                new ParkingVehicleServiceImpl(new MemoryVehicleStorage()),
                new ParkingLotEventManager());
    }

    @Test
    public void whenParkingLotHaveFreeCarSlotThanParkShouldReturnTicket() throws NoEmptySlotsException, VehicleParkedException {
        parkingLot.init(1, 0);
        var car = new Car();
        var ticket = parkingLot.park(car);

        assertNotNull(ticket);
        assertEquals(car, ticket.getVehicle());
    }

    @Test(expected = VehicleParkedException.class)
    public void whenParkTwiceSameVehicleThanExceptionShouldBeThrown() throws NoEmptySlotsException, VehicleParkedException {
        parkingLot.init(1, 0);
        var car = new Car();
        parkingLot.park(car);
        parkingLot.park(car);
    }

    @Test
    public void whenCarWasParkedThanAmountOfCarOccupiedSpotsShouldNotBeEmpty() throws NoEmptySlotsException, VehicleParkedException {
        parkingLot.init(1, 0);
        var car = new Car();
        parkingLot.park(car);

        assertThat(car.getSlots(), hasSize(1));
    }

    @Test
    public void whenParkingLotHaveFreeTruckSlotThanParkShouldReturnTicket() throws NoEmptySlotsException, VehicleParkedException {
        parkingLot.init(0, 1);
        var truck = new Truck(2);
        var ticket = parkingLot.park(truck);

        assertNotNull(ticket);
        assertEquals(truck, ticket.getVehicle());
    }

    @Test
    public void whenParkingLotHaveNoFreeTruckSlotButHaveFreeAmountOfCarSlotsThanParkShouldReturnTicket() throws NoEmptySlotsException, VehicleParkedException {
        parkingLot.init(2, 0);
        var ticket = parkingLot.park(new Truck(2));

        assertNotNull(ticket);
    }

    @Test(expected = NoEmptySlotsException.class)
    public void whenParkingLotHaveNoFreeSpaceThanExceptionShouldBeThrown() throws NoEmptySlotsException, VehicleParkedException {
        parkingLot.init(0, 0);
        parkingLot.park(new Car());
    }

    @Test(expected = VehicleNotParkedException.class)
    public void whenCarWasNotParkedThanUnparkShouldThrowException() throws VehicleNotParkedException {
        parkingLot.unpark(new Car());
    }

    @Test
    public void whenUnparkThanAmountOfCarOccupiedSpotsShouldBeEmpty() throws NoEmptySlotsException, VehicleParkedException, VehicleNotParkedException {
        parkingLot.init(1, 0);
        var car = new Car();
        parkingLot.park(car);

        assertThat(car.getSlots(), hasSize(1));

        parkingLot.unpark(car);

        assertThat(car.getSlots(), hasSize(0));
    }
}