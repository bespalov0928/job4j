package ru.job4j.design.lsp.parkinglot;

import ru.job4j.design.lsp.parkinglot.event.EventManager;
import ru.job4j.design.lsp.parkinglot.exception.NoEmptySlotsException;
import ru.job4j.design.lsp.parkinglot.exception.VehicleNotParkedException;
import ru.job4j.design.lsp.parkinglot.exception.VehicleParkedException;
import ru.job4j.design.lsp.parkinglot.service.FreeSlotService;
import ru.job4j.design.lsp.parkinglot.service.ParkingVehicleService;
import ru.job4j.design.lsp.parkinglot.slot.CarSlot;
import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.slot.TruckSlot;
import ru.job4j.design.lsp.parkinglot.ticket.Ticket;
import ru.job4j.design.lsp.parkinglot.vehicle.ParkingVehicle;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final FreeSlotService slotService;
    private final ParkingVehicleService vehicleService;
    private final EventManager eventManager;


    public ParkingLot(
            FreeSlotService slotService,
            ParkingVehicleService vehicleService,
            EventManager eventManager) {
        this.slotService = slotService;
        this.vehicleService = vehicleService;
        this.eventManager = eventManager;

        this.eventManager.subscribe(this.vehicleService);
        this.eventManager.subscribe(this.slotService);
    }

    public void init(int carSlotAmount, int truckSlotAmount) {
        var slots = new ArrayList<Slot>();
        for (int i = 0; i < carSlotAmount; i++) {
            slots.add(new CarSlot());
        }
        for (int i = 0; i < truckSlotAmount; i++) {
            slots.add(new TruckSlot());
        }
        slotService.add(slots);
    }

    public Ticket park(ParkingVehicle vehicle) throws VehicleParkedException, NoEmptySlotsException {
        isParked(vehicle);
        var slots = slotService.getFreeSlots(vehicle);
        isEmpty(slots);
        vehicle.setSlots(slots);
        eventManager.notify(vehicle);
        return new Ticket(vehicle);
    }

    public void unpark(ParkingVehicle vehicle) throws VehicleNotParkedException {
        isNotParked(vehicle);
        eventManager.notify(vehicle);
    }

    private void isParked(Vehicle vehicle) throws VehicleParkedException {
        if (vehicleService.isParked(vehicle)) {
            throw new VehicleParkedException();
        }
    }

    private void isNotParked(Vehicle vehicle) throws VehicleNotParkedException {
        if (!vehicleService.isParked(vehicle)) {
            throw new VehicleNotParkedException();
        }
    }

    private void isEmpty(List<Slot> list) throws NoEmptySlotsException {
        if (list.isEmpty()) {
            throw new NoEmptySlotsException();
        }
    }
}
