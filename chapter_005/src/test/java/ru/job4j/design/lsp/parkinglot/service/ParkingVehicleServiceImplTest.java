package ru.job4j.design.lsp.parkinglot.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.service.impl.ParkingVehicleServiceImpl;
import ru.job4j.design.lsp.parkinglot.storage.impl.MemoryVehicleStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;

import static org.junit.Assert.*;

public class ParkingVehicleServiceImplTest {
    private ParkingVehicleService service;

    @Before
    public void setUp() {
        service = new ParkingVehicleServiceImpl(new MemoryVehicleStorage());
    }

    @Test
    public void whenUpdateServiceByAlreadyParkedVehicleThanVehicleShouldBeRemovedFromService() {
        var vehicle = new Car();
        service.update(vehicle);
        var count = service.count();
        service.update(vehicle);

        assertEquals(count, service.count() + 1);
    }

    @Test
    public void whenUpdateServiceByNotParkedVehicleThanVehicleShouldBeAddedToService() {
        var vehicle = new Car();
        var count = service.count();
        service.update(vehicle);

        assertEquals(count, service.count() - 1);
    }

    @Test
    public void whenVehicleIsParkedThanIsParkedShouldReturnTrue() {
        var vehicle = new Car();
        service.update(vehicle);

        assertTrue(service.isParked(vehicle));
    }

    @Test
    public void whenVehicleIsNotParkedThanIsParkedShouldReturnFalse() {
        var vehicle = new Car();

        assertFalse(service.isParked(vehicle));
    }
}