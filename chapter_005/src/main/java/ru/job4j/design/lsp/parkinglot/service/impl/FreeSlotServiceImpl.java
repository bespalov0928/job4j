package ru.job4j.design.lsp.parkinglot.service.impl;

import ru.job4j.design.lsp.parkinglot.service.FreeSlotService;
import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.storage.SlotStorage;
import ru.job4j.design.lsp.parkinglot.vehicle.ParkingVehicle;

import java.util.List;

import static ru.job4j.design.lsp.parkinglot.vehicle.VehicleType.Car;
import static ru.job4j.design.lsp.parkinglot.vehicle.VehicleType.Truck;

public class FreeSlotServiceImpl implements FreeSlotService {
    private final SlotStorage storage;

    public FreeSlotServiceImpl(SlotStorage storage) {
        this.storage = storage;
    }

    @Override
    public List<Slot> getFreeSlots(ParkingVehicle vehicle) {
        List<Slot> slots = storage.findByVehicleType(vehicle.getType());
        if (Truck.equals(vehicle.getType()) && slots.isEmpty()) {
            slots = storage.findByVehicleType(Car);
            slots = slots.size() >= vehicle.getMaxOccupiedSlots() ? slots.subList(0, vehicle.getMaxOccupiedSlots()) : List.of();
        }
        return slots;
    }

    @Override
    public void add(List<Slot> slots) {
        slots.forEach(storage::add);
    }

    @Override
    public void remove(List<Slot> slots) {
        slots.forEach(storage::remove);
    }

    @Override
    public void update(ParkingVehicle vehicle) {
        var slots = this.getFreeSlots(vehicle);
        if (slots.containsAll(vehicle.getSlots())) {
            this.remove(vehicle.getSlots());
        } else {
            this.add(vehicle.getSlots());
            vehicle.setSlots(List.of());
        }
    }
}
