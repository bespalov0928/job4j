package ru.job4j.design.lsp.parkinglot;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.event.impl.ParkingServiceEventManager;
import ru.job4j.design.lsp.parkinglot.exception.ParkingLotFullException;
import ru.job4j.design.lsp.parkinglot.exception.VehicleAlreadyParkedException;
import ru.job4j.design.lsp.parkinglot.service.impl.FreeSlotServiceImpl;
import ru.job4j.design.lsp.parkinglot.service.impl.ParkingVehicleServiceImpl;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;
import ru.job4j.design.lsp.parkinglot.vehicle.Truck;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {
    private ParkingLot parkingLot;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot(
                new FreeSlotServiceImpl(),
                new ParkingVehicleServiceImpl(),
                new ParkingServiceEventManager());
    }

    @Test
    public void whenParkingLotHaveFreeCarSlotThanParkShouldReturnTicket() {
        parkingLot.init(1, 0);
        var car = new Car();
        var ticket = parkingLot.park(car);
        assertNotNull(ticket);
        assertEquals(car, ticket.getVehicle());
    }

    @Test(expected = VehicleAlreadyParkedException.class)
    public void whenParkTwiceSameVehicleThanExceptionShouldBeThrown() {
        parkingLot.init(1, 0);
        var car = new Car();
        parkingLot.park(car);
        parkingLot.park(car);
    }

    @Test
    public void whenCarWasParkedThanAmountOfCarOccupiedSpotsShouldNotBeEmpty() {
        parkingLot.init(1, 0);
        var car = new Car();
        parkingLot.park(car);
        assertThat(car.getSlots(), hasSize(1));
    }

    @Test
    public void whenParkingLotHaveFreeTruckSlotThanParkShouldReturnTicket() {
        parkingLot.init(0, 1);
        var truck = new Truck(2);
        var ticket = parkingLot.park(truck);
        assertNotNull(ticket);
        assertEquals(truck, ticket.getVehicle());
    }

    @Test
    public void whenParkingLotHaveNoFreeTruckSlotButHaveFreeAmountOfCarSlotsThanParkShouldReturnTicket() {
        parkingLot.init(2, 0);
        var ticket = parkingLot.park(new Truck(2));
        assertNotNull(ticket);
    }

    @Test(expected = ParkingLotFullException.class)
    public void whenParkingLotHaveNoFreeSpaceThanExceptionShouldBeThrown() {
        parkingLot.init(0, 0);
        parkingLot.park(new Car());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCarWasNotParkedThanUnparkShouldThrowException() {
        parkingLot.init(0, 0);
        parkingLot.unpark(new Car());
    }

    @Test
    public void whenUnparkThanAmountOfCarOccupiedSpotsShouldBeEmpty() {
        parkingLot.init(1, 0);
        var car = new Car();
        parkingLot.park(car);
        assertThat(car.getSlots(), hasSize(1));
        parkingLot.unpark(car);
        assertThat(car.getSlots(), hasSize(0));
    }
}