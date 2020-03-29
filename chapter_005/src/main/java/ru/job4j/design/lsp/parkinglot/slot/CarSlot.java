package ru.job4j.design.lsp.parkinglot.slot;

import ru.job4j.design.lsp.parkinglot.vehicle.VehicleType;

public class CarSlot extends AbstractSlot {

    public CarSlot() {
        super(VehicleType.Car);
    }
}
