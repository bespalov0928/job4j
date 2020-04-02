package ru.job4j.design.lsp.parkinglot.event;

import ru.job4j.design.lsp.parkinglot.vehicle.ParkingVehicle;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

public interface EventListener {

    void update(ParkingVehicle vehicle);
}
