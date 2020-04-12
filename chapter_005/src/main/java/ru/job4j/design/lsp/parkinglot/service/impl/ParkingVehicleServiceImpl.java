package ru.job4j.design.lsp.parkinglot.service.impl;

import ru.job4j.design.lsp.parkinglot.service.ParkingVehicleService;
import ru.job4j.design.lsp.parkinglot.storage.VehicleStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.ParkingVehicle;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

public class ParkingVehicleServiceImpl implements ParkingVehicleService {
    private final VehicleStorage storage;

    public ParkingVehicleServiceImpl(VehicleStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean isParked(Vehicle vehicle) {
        var result = storage.add(vehicle);
        if (result) {
            storage.remove(vehicle);
        }
        return !result;
    }

    @Override
    public int count() {
        return storage.count();
    }

    @Override
    public void update(ParkingVehicle vehicle) {
        if (!storage.add(vehicle)) {
            storage.remove(vehicle);
        }
    }
}
