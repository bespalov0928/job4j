package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "Find Item by Id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter item Id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        } else {
            System.out.print("Item not found!");
        }
        return true;
    }
}
