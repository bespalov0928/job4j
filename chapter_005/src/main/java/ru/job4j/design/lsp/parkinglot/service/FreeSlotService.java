package ru.job4j.design.lsp.parkinglot.service;

import ru.job4j.design.lsp.parkinglot.slot.Slot;
import ru.job4j.design.lsp.parkinglot.vehicle.ParkingVehicle;

import java.util.List;

public interface FreeSlotService extends SlotService {

    List<Slot> getFreeSlots(ParkingVehicle vehicle);
}
