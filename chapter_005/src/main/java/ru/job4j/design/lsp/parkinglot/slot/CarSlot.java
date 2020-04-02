package ru.job4j.design.lsp.parkinglot.slot;

import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

public class CarSlot implements Slot {
    private final VehicleType vehicleType = VehicleType.Car;

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
