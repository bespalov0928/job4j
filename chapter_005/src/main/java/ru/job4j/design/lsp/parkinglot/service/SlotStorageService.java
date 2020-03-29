package ru.job4j.design.lsp.parkinglot.service;

import ru.job4j.design.lsp.parkinglot.event.EventListener;
import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

import java.util.List;

public interface SlotStorageService extends EventListener {
    void add(Slot slot);

    void remove(Slot slot);

    Slot get();

    List<Slot> getSlots(Vehicle vehicle);
}
