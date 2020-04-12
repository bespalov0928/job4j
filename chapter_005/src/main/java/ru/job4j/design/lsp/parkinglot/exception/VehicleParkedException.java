package ru.job4j.design.lsp.parkinglot.exception;

public class VehicleParkedException extends Exception {
    public VehicleParkedException() {
        super("Vehicle already parked!");
    }
}
