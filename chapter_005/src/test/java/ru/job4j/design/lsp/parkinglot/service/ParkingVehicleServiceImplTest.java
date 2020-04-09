package ru.job4j.design.lsp.parkinglot.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.design.lsp.parkinglot.service.impl.ParkingVehicleServiceImpl;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Ignore
public class ParkingVehicleServiceImplTest {
    private ParkingVehicleService parkedVehicles;

    @Before
    public void setUp() {
        parkedVehicles = new ParkingVehicleServiceImpl();
    }

    @Test
    public void whenAddVehicleThanCountShouldChange() {
        var count = parkedVehicles.count();
        parkedVehicles.add(new Car());

        assertNotEquals(count, parkedVehicles.count());
    }

    @Test
    public void whenRemoveVehicleThanCountShouldChange() {
        var vehicle = new Car();
        parkedVehicles.add(vehicle);
        var count = parkedVehicles.count();
        parkedVehicles.remove(vehicle);

        assertNotEquals(count, parkedVehicles.count());
    }

    @Test
    public void whenUpdateServiceByAlreadyParkedVehicleThanVehicleShouldBeRemovedFromService() {
        var vehicle = new Car();
        parkedVehicles.add(vehicle);
        var count = parkedVehicles.count();
        parkedVehicles.update(vehicle);

        assertEquals(count, parkedVehicles.count() + 1);
    }

    @Test
    public void whenUpdateServiceByNotParkedVehicleThanVehicleShouldBeAddedToService() {
        var vehicle = new Car();
        var count = parkedVehicles.count();
        parkedVehicles.update(vehicle);

        assertEquals(count, parkedVehicles.count() - 1);
    }
}