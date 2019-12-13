package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "Delete Item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter item Id: ");
        if (tracker.delete(id)) {
            System.out.print("Item successfully deleted");
        } else {
            System.out.print("Item not found. Id is incorrect!");
        }
        return true;
    }
}
