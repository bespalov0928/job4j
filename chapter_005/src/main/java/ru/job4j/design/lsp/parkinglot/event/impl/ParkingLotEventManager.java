package ru.job4j.design.lsp.parkinglot.event.impl;

import ru.job4j.design.lsp.parkinglot.event.EventListener;
import ru.job4j.design.lsp.parkinglot.event.EventManager;
import ru.job4j.design.lsp.parkinglot.vehicle.ParkingVehicle;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

import java.util.HashSet;
import java.util.Set;

public class ParkingLotEventManager implements EventManager {
    private final Set<EventListener> listeners = new HashSet<>();

    @Override
    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notify(ParkingVehicle vehicle) {
        listeners.forEach(listener -> listener.update(vehicle));
    }
}
