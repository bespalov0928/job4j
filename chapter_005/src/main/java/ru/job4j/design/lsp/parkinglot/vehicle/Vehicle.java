package ru.job4j.design.lsp.parkinglot.vehicle;

import ru.job4j.design.lsp.parkinglot.slot.Slot;

import java.util.List;

public interface Vehicle {
    void addSlot(Slot slot);

    VehicleType getType();

    List<Slot> getSlots();
}
