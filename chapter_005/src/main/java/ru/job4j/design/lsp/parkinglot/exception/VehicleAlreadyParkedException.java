package ru.job4j.design.lsp.parkinglot.exception;

public class VehicleAlreadyParkedException extends Exception {
    public VehicleAlreadyParkedException() {
        super("Vehicle already parked!");
    }
}
