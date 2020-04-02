package ru.job4j.design.lsp.parkinglot.ticket;

import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

import java.time.LocalDateTime;

public class Ticket {
    private final LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime closeDate;
    private final Vehicle vehicle;

    public Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void close() {
        closeDate = LocalDateTime.now();
    }
}
