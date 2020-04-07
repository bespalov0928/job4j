package ru.job4j.design.isp.service;

import ru.job4j.design.isp.menu.item.ActionMenuItem;
import ru.job4j.design.isp.menu.item.MenuItem;
import ru.job4j.design.isp.storage.MenuItemStorage;

public class ActionMenuItemFinderService implements MenuItemFinderService<ActionMenuItem> {
    private final MenuItemStorage<? extends MenuItem> storage;

    public ActionMenuItemFinderService(MenuItemStorage<? extends MenuItem> storage) {
        this.storage = storage;
    }

    @Override
    public ActionMenuItem findByIndex(int index) {
        ActionMenuItem result = null;
        var it = storage.iterator();
        int i = -1;
        while (i++ < index && it.hasNext()) {
            result = (ActionMenuItem) it.next();
        }
        return result;
    }
}
