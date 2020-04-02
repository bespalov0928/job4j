package ru.job4j.design.lsp.parkinglot.service.impl;

import ru.job4j.design.lsp.parkinglot.service.FreeSlotService;
import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.vehicle.ParkingVehicle;
import ru.job4j.design.lsp.parkinglot.vehicle.Vehicle;

import java.util.List;

public class FreeSlotServiceImpl implements FreeSlotService {

    @Override
    public List<Slot> getFreeSlots(Vehicle vehicle) {
        return null;
    }

    @Override
    public void add(List<Slot> slots) {

    }

    @Override
    public void remove(List<Slot> slots) {

    }

    @Override
    public void update(ParkingVehicle vehicle) {

    }
}
