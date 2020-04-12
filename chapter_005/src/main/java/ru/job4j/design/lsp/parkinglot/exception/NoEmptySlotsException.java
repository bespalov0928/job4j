package ru.job4j.design.lsp.parkinglot.exception;

public class NoEmptySlotsException extends Exception {
    public NoEmptySlotsException() {
        super("No empty slots!");
    }
}
