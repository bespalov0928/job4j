package ru.job4j.design.lsp.parkinglot.slot;

import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

public class TruckSlot extends AbstractSlot {

    public TruckSlot() {
        super(VehicleType.Truck);
    }
}
