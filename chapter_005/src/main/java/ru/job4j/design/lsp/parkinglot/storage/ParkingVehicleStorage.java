package ru.job4j.design.lsp.parkinglot.storage;

import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

public interface ParkingVehicleStorage {
    void add(Vehicle vehicle);

    void remove(Vehicle vehicle);

    int count();
}
