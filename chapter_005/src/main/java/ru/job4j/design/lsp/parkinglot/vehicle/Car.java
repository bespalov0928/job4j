package ru.job4j.design.lsp.parkinglot.vehicle;

import ru.job4j.design.lsp.parkinglot.slot.Slot;

import java.util.ArrayList;
import java.util.List;

public class Car implements NumberPlateVehicle, ParkingVehicle {
    private final VehicleType vehicleType = VehicleType.Car;
    private final String numberPlate = generateNumberPlate();
    private final List<Slot> slots = new ArrayList<>();

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
        return 1;
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
