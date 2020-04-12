package ru.job4j.design.lsp.parkinglot.event;

import ru.job4j.design.lsp.parkinglot.vehicle.ParkingVehicle;

public interface EventManager {
    void subscribe(EventListener listener);

    void unsubscribe(EventListener listener);

    void notify(ParkingVehicle vehicle);
}
