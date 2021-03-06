package ru.job4j.design.lsp.parkinglot.storage;

import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

public interface VehicleStorage {
    boolean add(Vehicle vehicle);

    void remove(Vehicle vehicle);

    int count();
}
