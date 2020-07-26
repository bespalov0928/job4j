package ru.job4j.tracker;

import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.tracker.ITracker;

import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private final Input input;
    private final ITracker tracker;
    private final Consumer<String> output;
    private final List<UserAction> actions;

    public MenuTracker(Input input, ITracker tracker, Consumer<String> output, List<UserAction> actions) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
        this.actions = actions;
    }

    public void showMenu() {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Input number of operation: ", actions.size());
            UserAction action = actions.get(select);
            output.accept(action.name());
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println();
        for (int index = 0; index < actions.size(); index++) {
            output.accept(index + ". " + actions.get(index).name());
        }
    }
}
