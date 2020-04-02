package ru.job4j.design.lsp.parkinglot.slot;

import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

public class TruckSlot implements Slot {
    private final VehicleType vehicleType = VehicleType.Truck;

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
