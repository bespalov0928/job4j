package ru.job4j.design.lsp.parkinglot.vehicle;

import ru.job4j.design.lsp.parkinglot.slot.Slot;

import java.util.List;

public interface ParkingVehicle extends Vehicle {
    int getMaxOccupiedSlots();

    void setSlots(List<Slot> slots);

    List<Slot> getSlots();
}
