package ru.job4j.design.lsp.parkinglot.exception;

public class VehicleNotParkedException extends Exception {
    public VehicleNotParkedException() {
        super("Vehicle not parked!");
    }
}
