package ru.job4j.design.lsp.parkinglot.vehicle;

import ru.job4j.design.lsp.parkinglot.slot.Slot;

import java.util.ArrayList;
import java.util.List;

public class Truck implements NumberPlateVehicle, ParkingVehicle {
    private final VehicleType vehicleType = VehicleType.Truck;
    private final String numberPlate = generateNumberPlate();
    private final int maxOccupiedSlots;
    private final List<Slot> slots = new ArrayList<>();

    public Truck(int maxOccupiedSlots) {
        this.maxOccupiedSlots = maxOccupiedSlots;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public String getNumberPlate() {
        return numberPlate;
    }

    @Override
    public int getMaxOccupiedSlots() {
        return maxOccupiedSlots;
    }

    @Override
    public void setSlots(List<Slot> slots) {
        this.slots.clear();
        this.slots.addAll(slots);
    }

    @Override
    public List<Slot> getSlots() {
        return slots;
    }
}
