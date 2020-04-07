package ru.job4j.design.isp;

import ru.job4j.design.isp.action.ExitAction;
import ru.job4j.design.isp.action.PrintAction;
import ru.job4j.design.isp.input.ConsoleInput;
import ru.job4j.design.isp.menu.ConsoleMenu;
import ru.job4j.design.isp.menu.item.LevelMenuItem;
import ru.job4j.design.isp.menu.item.MenuItemImpl;
import ru.job4j.design.isp.menu.MenuManager;
import ru.job4j.design.isp.service.ActionMenuItemFinderService;
import ru.job4j.design.isp.status.ExitStatus;
import ru.job4j.design.isp.storage.impl.FormattedTreeMenuItemStorage;
import ru.job4j.design.isp.storage.TreeMenuItemStorage;
import ru.job4j.design.isp.storage.impl.TreeMenuItemStorageImpl;

public class Application {
    public static void main(String[] args) {
        var exitStatus = new ExitStatus();
        TreeMenuItemStorage<LevelMenuItem> items = new TreeMenuItemStorageImpl();
        items.add(new MenuItemImpl("Задача 1", new PrintAction("Print action have been executed.")), null);
        items.add(new MenuItemImpl("Задача 1.1"), new MenuItemImpl("Задача 1"));
        items.add(new MenuItemImpl("Задача 1.1.1"), new MenuItemImpl("Задача 1.1"));
        items.add(new MenuItemImpl("Задача 1.1.2"), new MenuItemImpl("Задача 1.1"));
        items.add(new MenuItemImpl("Задача 1.2"), new MenuItemImpl("Задача 1"));
        items.add(new MenuItemImpl("Задача 2"), null);
        items.add(new MenuItemImpl("Задача 2.1"), new MenuItemImpl("Задача 2"));
        items.add(new MenuItemImpl("Exit System", new ExitAction(exitStatus)), null);

        new MenuManager(
                new ConsoleMenu(new FormattedTreeMenuItemStorage(items)),
                new ConsoleInput(),
                new ActionMenuItemFinderService(items), exitStatus).run();
    }
}
