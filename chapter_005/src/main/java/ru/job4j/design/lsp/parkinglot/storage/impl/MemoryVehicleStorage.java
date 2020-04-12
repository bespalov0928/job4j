package ru.job4j.design.lsp.parkinglot.storage.impl;

import ru.job4j.design.lsp.parkinglot.storage.VehicleStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

import java.util.HashSet;
import java.util.Set;

public class MemoryVehicleStorage implements VehicleStorage {
    private final Set<Vehicle> vehicles = new HashSet<>();

    @Override
    public boolean add(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }

    @Override
    public void remove(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    @Override
    public int count() {
        return vehicles.size();
    }
}
