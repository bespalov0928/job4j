package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.tracker.ITracker;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "Edit Item";
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        long id = Long.parseLong(input.askStr("Enter item id:"));
        String name = input.askStr("Enter a new name of item: ");
        Item item = new Item(name);
        item.setId(id);
        if (tracker.replace(id, item)) {
            System.out.print("Item successfully replaced");
        } else {
            System.out.print("Item not found. Id is incorrect!");
        }
        return true;
    }
}
