package ru.job4j.design.lsp.parkinglot.vehicle;

import ru.job4j.design.lsp.parkinglot.slot.Slot;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class AbstractVehicle implements Vehicle {
    private final int occupySlots;
    private final VehicleType type;
    private final String numberPlate = generateNumberPlate();
    private final List<Slot> slots = new ArrayList<>();

    public AbstractVehicle(VehicleType type, int occupySlots) {
        this.occupySlots = occupySlots;
        this.type = type;
    }

    @Override
    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public List<Slot> getSlots() {
        return slots;
    }

    private String generateNumberPlate() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractVehicle that = (AbstractVehicle) o;
        return type == that.type
                && numberPlate.equals(that.numberPlate)
                && slots.equals(that.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, numberPlate);
    }
}
