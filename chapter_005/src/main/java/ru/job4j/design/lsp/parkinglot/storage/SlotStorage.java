package ru.job4j.design.lsp.parkinglot.storage;

import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

import java.util.List;

public interface SlotStorage {
    void add(Slot slot);

    void remove(Slot slot);

    Slot findByVehicleType(VehicleType vehicleType);

    List<Slot> findNearby(int amount, VehicleType vehicleType);
}
