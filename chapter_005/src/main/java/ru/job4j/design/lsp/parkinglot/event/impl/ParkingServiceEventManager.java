package ru.job4j.design.lsp.parkinglot.event.impl;

import ru.job4j.design.lsp.parkinglot.event.EventListener;
import ru.job4j.design.lsp.parkinglot.event.EventManager;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

public class ParkingServiceEventManager implements EventManager {
    @Override
    public void subscribe(EventListener listener) {

    }

    @Override
    public void unsubscribe(EventListener listener) {

    }

    @Override
    public void notify(Vehicle vehicle) {

    }
}
