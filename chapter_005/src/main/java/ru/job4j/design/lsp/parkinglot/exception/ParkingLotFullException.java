package ru.job4j.design.lsp.parkinglot.exception;

public class ParkingLotFullException extends Exception {
    public ParkingLotFullException() {
        super("Parking lot is full!");
    }
}
