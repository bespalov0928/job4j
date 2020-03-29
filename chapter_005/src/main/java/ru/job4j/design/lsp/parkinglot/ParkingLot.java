package ru.job4j.design.lsp.parkinglot;

import ru.job4j.design.lsp.parkinglot.event.EventManager;
import ru.job4j.design.lsp.parkinglot.service.SlotStorageService;
import ru.job4j.design.lsp.parkinglot.service.VehicleStorageService;
import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.ticket.Ticket;
import ru.job4j.design.lsp.parkinglot.vehicle.Car;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

import java.util.List;

public class ParkingLot {
    private final SlotStorageService freeSlotStorageService;
    private final VehicleStorageService vehicleStorageService;
    private final EventManager parkingEventManager;


    public ParkingLot(
            SlotStorageService slotStorageService,
            VehicleStorageService vehicleStorageService,
            EventManager parkingEventManager) {
        this.freeSlotStorageService = slotStorageService;
        this.vehicleStorageService = vehicleStorageService;
        this.parkingEventManager = parkingEventManager;

        this.parkingEventManager.subscribe(this.vehicleStorageService);
        this.parkingEventManager.subscribe(this.freeSlotStorageService);
    }

    public Ticket park(Vehicle vehicle) {
        List<Slot> freeSlots = freeSlotStorageService.getSlots(vehicle);
        if (freeSlots.size() > 0) {
            freeSlots.forEach(vehicle::addSlot);
            parkingEventManager.notify(vehicle);
        }

        return new Ticket(vehicle);
    }

    public void unpark(Vehicle vehicle) {
        parkingEventManager.notify(vehicle);
    }

    public static void main(String[] args) {
        System.out.println(new Car());
    }
}
