package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.tracker.ITracker;

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
    public boolean execute(Input input, ITracker tracker) {
        long id = Long.parseLong(input.askStr("Enter item Id: "));
        Item item = tracker.findById(id);
        if (item != null) {
            output.accept("Name: " + item.getName() + ", Id: " + item.getId());
        } else {
            output.accept("Item not found!");
        }
        return true;
    }
}
