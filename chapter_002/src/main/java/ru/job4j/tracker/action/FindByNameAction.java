package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.tracker.ITracker;

import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "Find Items by Name";
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String name = input.askStr("Enter item name: ");
        List<Item> items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        }
        if (items.size() == 0) {
            System.out.print("Item not found!");
        }
        return true;
    }
}
