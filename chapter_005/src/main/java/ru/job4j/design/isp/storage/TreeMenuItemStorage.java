package ru.job4j.design.isp.storage;

import ru.job4j.design.isp.menu.item.MenuItem;

public interface TreeMenuItemStorage<T extends MenuItem> extends MenuItemStorage<T> {
    void add(T item, T parent);
}
