package ru.job4j.design.lsp.parkinglot.storage.impl;

import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.storage.SlotStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemorySlotStorage implements SlotStorage {
    private final List<Slot> slots = new ArrayList<>();

    @Override
    public void add(Slot slot) {
        slots.add(slot);
    }

    @Override
    public void remove(Slot slot) {
        slots.remove(slot);
    }

    @Override
    public List<Slot> findByVehicleType(VehicleType vehicleType) {
        return slots.stream()
                .filter(slot -> vehicleType.equals(slot.getVehicleType()))
                .collect(Collectors.toList());
    }
}
