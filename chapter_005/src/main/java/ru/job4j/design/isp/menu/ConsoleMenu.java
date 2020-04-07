package ru.job4j.design.isp.menu;

import ru.job4j.design.isp.menu.item.MenuItem;
import ru.job4j.design.isp.storage.MenuItemStorage;

public class ConsoleMenu implements Menu {
    private final MenuItemStorage<? extends MenuItem> menuItems;

    public ConsoleMenu(MenuItemStorage<? extends MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public void display() {
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem.getTitle());
        }
    }
}
