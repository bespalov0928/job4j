package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {
    private final Input input;
    private final Tracker tracker;
    private final Consumer<String> output;

    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void init(List<UserAction> actions) {
        MenuTracker menuTracker = new MenuTracker(input, tracker, output, actions);
        menuTracker.showMenu();
    }

    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        Tracker tracker = new Tracker();
        new StartUI(input, tracker, System.out::println).init(List.of(
                new CreateAction(),
                new ShowAllAction(),
                new EditAction(),
                new DeleteAction(),
                new FindByIdAction(System.out::println),
                new FindByNameAction(),
                new ExitAction()
        ));
    }
}
