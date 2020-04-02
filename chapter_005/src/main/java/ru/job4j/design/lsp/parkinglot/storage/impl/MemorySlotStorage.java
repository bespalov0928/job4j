package ru.job4j.design.lsp.parkinglot.storage.impl;

import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.storage.SlotStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

import java.util.List;

public class MemorySlotStorage implements SlotStorage {
    @Override
    public void add(Slot slot) {

    }

    @Override
    public void remove(Slot slot) {

    }

    @Override
    public List<Slot> findByVehicleType(VehicleType vehicleType) {
        return null;
    }
}
