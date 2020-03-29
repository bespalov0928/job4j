package ru.job4j.design.lsp.parkinglot.service;

import ru.job4j.design.lsp.parkinglot.event.EventListener;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

public interface VehicleStorageService extends EventListener {
    void add(Vehicle vehicle);

    void remove(Vehicle vehicle);
}