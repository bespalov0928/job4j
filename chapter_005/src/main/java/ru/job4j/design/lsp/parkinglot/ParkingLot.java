package ru.job4j.design.lsp.parkinglot;

import ru.job4j.design.lsp.parkinglot.event.EventManager;
import ru.job4j.design.lsp.parkinglot.service.FreeSlotService;
import ru.job4j.design.lsp.parkinglot.service.ParkingVehicleService;
import ru.job4j.design.lsp.parkinglot.ticket.Ticket;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

public class ParkingLot {
    private final FreeSlotService freeSlotService;
    private final ParkingVehicleService parkingVehicleService;
    private final EventManager parkingEventManager;


    public ParkingLot(
            FreeSlotService freeSlotService,
            ParkingVehicleService parkingVehicleService,
            EventManager parkingEventManager) {
        this.freeSlotService = freeSlotService;
        this.parkingVehicleService = parkingVehicleService;
        this.parkingEventManager = parkingEventManager;

        this.parkingEventManager.subscribe(this.parkingVehicleService);
        this.parkingEventManager.subscribe(this.freeSlotService);
    }

    public void init(int carSlotAmount, int truckSlotAmount) {

    }

    public Ticket park(Vehicle vehicle) {
        return null;
    }

    public void unpark(Vehicle vehicle) {

    }
}
