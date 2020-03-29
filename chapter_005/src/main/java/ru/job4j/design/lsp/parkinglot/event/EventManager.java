package ru.job4j.design.lsp.parkinglot.event;

import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

public interface EventManager {
    void subscribe(EventListener listener);

    void unsubscribe(EventListener listener);

    void notify(Vehicle vehicle);
}
