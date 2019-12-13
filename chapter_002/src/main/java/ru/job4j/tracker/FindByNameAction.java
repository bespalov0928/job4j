package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "Find Items by Name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter item name: ");
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        }
        if (items.length == 0) {
            System.out.print("Item not found!");
        }
        return true;
    }
}
