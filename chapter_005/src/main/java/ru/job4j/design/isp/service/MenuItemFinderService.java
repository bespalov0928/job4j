package ru.job4j.design.isp.service;

import ru.job4j.design.isp.menu.item.MenuItem;

public interface MenuItemFinderService<T extends MenuItem> {
    T findByIndex(int index);
}
