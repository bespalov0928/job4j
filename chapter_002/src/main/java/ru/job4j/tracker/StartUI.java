package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {
    private static final String[] MENU_ITEMS = {
            "0. Add new Item",
            "1. Show all items",
            "2. Edit item",
            "3. Delete item",
            "4. Find item by Id",
            "5. Find items by name",
            "6. Exit Program"
    };

    public void init(Input input, Tracker tracker) {
        int select = 0;
        while (select != 6) {
            this.showMenu();
            select = input.askInt("Input number of operation:");
            if (select == 0) {
                StartUI.addNewItem(input, tracker);
            } else if (select == 1) {
                StartUI.showAllItems(tracker);
            } else if (select == 2) {
                StartUI.replaceItem(input, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);
            } else if (select == 4) {
                StartUI.findItemById(input, tracker);
            } else if (select == 5) {
                StartUI.findItemsByName(input, tracker);
            }
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("Menu.");
        for (String menuItem : MENU_ITEMS) {
            System.out.println(menuItem);
        }
    }

    protected static void addNewItem(Input input, Tracker tracker) {
        System.out.println("=== Create a New Item ===");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    private static void showAllItems(Tracker tracker) {
        System.out.println("=== Show All Items ===");
        Item[] items = tracker.findAll();
        for (Item item : items) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        }
    }

    protected static void replaceItem(Input input, Tracker tracker) {
        System.out.println(" === Update item ====");
        String id = input.askStr("Enter item id:");
        String name = input.askStr("Enter a new name of item: ");
        Item item = new Item(name);
        item.setId(id);
        if (tracker.replace(id, item)) {
            System.out.print("Item successfully replaced");
        } else {
            System.out.print("Item not found. Id is incorrect!");
        }
    }

    protected static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete Item ===");
        String id = input.askStr("Enter item Id: ");
        if (tracker.delete(id)) {
            System.out.print("Item successfully deleted");
        } else {
            System.out.print("Item not found. Id is incorrect!");
        }
    }

    private static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find Item By Id ===");
        String id = input.askStr("Enter item Id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        } else {
            System.out.print("Item not found!");
        }
    }

    private static void findItemsByName(Input input, Tracker tracker) {
        System.out.println("=== Find Items By Name ===");
        System.out.print("Enter item name: ");
        String name = input.askStr("Enter new name: ");
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        }
        if (items.length == 0) {
            System.out.print("Item not found!");
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}