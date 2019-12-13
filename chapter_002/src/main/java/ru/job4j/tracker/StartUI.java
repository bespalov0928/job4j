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

    public void init(Scanner scanner, Tracker tracker) {
        int select = 0;
        while (select != 6) {
            this.showMenu();
            select = Integer.parseInt(scanner.nextLine());
            if (select == 0) {
                addNewItem(scanner, tracker);
            } else if (select == 1) {
                showAllItems(tracker);
            } else if (select == 2) {
                editItem(scanner, tracker);
            } else if (select == 3) {
                deleteItem(scanner, tracker);
            } else if (select == 4) {
                findItemById(scanner, tracker);
            } else if (select == 5) {
                findItemsByName(scanner, tracker);
            }
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("Menu.");
        for (String menuItem : MENU_ITEMS) {
            System.out.println(menuItem);
        }
        System.out.println("Select:");
    }

    private void addNewItem(Scanner scanner, Tracker tracker) {
        System.out.println("=== Create a New Item ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        Item item = new Item(name);
        tracker.add(item);
    }

    private void showAllItems(Tracker tracker) {
        System.out.println("=== Show All Items ===");
        Item[] items = tracker.findAll();
        for (Item item : items) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        }
    }

    private void editItem(Scanner scanner, Tracker tracker) {
        System.out.println("=== Edit Item ===");
        System.out.print("Enter item Id: ");
        String id = scanner.nextLine();
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            item.setName(name);
        } else {
            System.out.print("Item not found. Id is incorrect!");
        }
    }

    private void deleteItem(Scanner scanner, Tracker tracker) {
        System.out.println("=== Delete Item ===");
        System.out.print("Enter item Id: ");
        String id = scanner.nextLine();
        if (tracker.delete(id)) {
            System.out.print("Item successfully deleted");
        } else {
            System.out.print("Item not found. Id is incorrect!");
        }
    }

    private void findItemById(Scanner scanner, Tracker tracker) {
        System.out.println("=== Find Item By Id ===");
        System.out.print("Enter item Id: ");
        String id = scanner.nextLine();
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        } else {
            System.out.print("Item not found!");
        }
    }

    private void findItemsByName(Scanner scanner, Tracker tracker) {
        System.out.println("=== Find Items By Name ===");
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println("Name: " + item.getName() + ", Id: " + item.getId());
        }
        if (items.length == 0) {
            System.out.print("Item not found!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}
