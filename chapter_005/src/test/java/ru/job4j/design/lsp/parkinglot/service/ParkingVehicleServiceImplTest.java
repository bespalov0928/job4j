package ru.job4j.design.lsp.parkinglot.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.service.impl.ParkingVehicleServiceImpl;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ParkingVehicleServiceImplTest {
    private ParkingVehicleService parkingVehicleService;

    @Before
    public void setUp() {
        parkingVehicleService = new ParkingVehicleServiceImpl();
    }

    @Test
    public void whenAddVehicleThanCountShouldChange() {
        var previousCount = parkingVehicleService.count();
        parkingVehicleService.add(new Car());
        assertNotEquals(previousCount, parkingVehicleService.count());
    }

    @Test
    public void whenRemoveVehicleThanCountShouldChange() {
        var vehicle = new Car();
        parkingVehicleService.add(vehicle);
        var previousCount = parkingVehicleService.count();
        parkingVehicleService.remove(vehicle);
        assertNotEquals(previousCount, parkingVehicleService.count());
    }

    @Test
    public void whenUpdateServiceByAlreadyParkedVehicleThanVehicleShouldBeRemovedFromService() {
        var vehicle = new Car();
        parkingVehicleService.add(vehicle);
        var previousCount = parkingVehicleService.count();
        parkingVehicleService.update(vehicle);
        assertEquals(previousCount, parkingVehicleService.count() + 1);
    }

    @Test
    public void whenUpdateServiceByNotParkedVehicleThanVehicleShouldBeAddedToService() {
        var vehicle = new Car();
        var previousCount = parkingVehicleService.count();
        parkingVehicleService.update(vehicle);
        assertEquals(previousCount, parkingVehicleService.count() - 1);
    }
}