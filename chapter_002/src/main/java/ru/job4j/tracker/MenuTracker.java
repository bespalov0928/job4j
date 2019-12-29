package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private final Input input;
    private final Tracker tracker;
    private final Consumer<String> output;
    private final List<UserAction> actions;

    public MenuTracker(Input input, Tracker tracker, Consumer<String> output, List<UserAction> actions) {
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
