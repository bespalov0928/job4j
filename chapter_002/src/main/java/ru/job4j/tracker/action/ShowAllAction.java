package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.tracker.ITracker;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        for (Item item : tracker.findAll()) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        }
        return true;
    }
}
