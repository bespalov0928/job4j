package ru.job4j.tracker;

import java.util.Collection;
import java.util.function.Consumer;

public class FindByIdAction implements UserAction {
    private final Consumer<String> output;

    public FindByIdAction(Consumer<String> output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Find Item by Id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter item Id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            output.accept("Name: " + item.getName() + ", Id: " + item.getId());
        } else {
            output.accept("Item not found!");
        }
        return true;
    }
}
