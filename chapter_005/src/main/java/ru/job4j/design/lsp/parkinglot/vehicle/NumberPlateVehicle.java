package ru.job4j.design.lsp.parkinglot.vehicle;

import java.util.UUID;

public interface NumberPlateVehicle extends Vehicle {
    String getNumberPlate();

    default String generateNumberPlate() {
        return UUID.randomUUID().toString().substring(0, 7);
    }
}
