package ru.job4j.design.lsp.parkinglot.service;

import ru.job4j.design.lsp.parkinglot.event.EventListener;
import ru.job4j.design.lsp.parkinglot.slot.Slot;

import java.util.List;

public interface SlotService extends EventListener {
    void add(List<Slot> slots);

    void remove(List<Slot> slots);
}
