package ru.job4j.design.lsp.parkinglot.vehicle;

public class Truck extends AbstractVehicle {
    public Truck(int occupySlots) {
        super(VehicleType.Truck, occupySlots);
    }
}
