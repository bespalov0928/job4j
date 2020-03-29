package ru.job4j.design.lsp.parkinglot.slot;

import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

public class AbstractSlot implements Slot {
    private final VehicleType vehicleType;

    public AbstractSlot(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
