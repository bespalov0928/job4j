package ru.job4j.design.isp.service;

import org.junit.Test;
import ru.job4j.design.isp.menu.item.ActionMenuItem;
import ru.job4j.design.isp.menu.item.LevelMenuItem;
import ru.job4j.design.isp.menu.item.MenuItemImpl;
import ru.job4j.design.isp.storage.TreeMenuItemStorage;
import ru.job4j.design.isp.storage.impl.TreeMenuItemStorageImpl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ActionMenuItemFinderServiceTest {

    @Test
    public void whenStorageHaveItemsThanIteratorNextShouldReturnThemInCorrectOrder() {
        TreeMenuItemStorage<LevelMenuItem> storage = new TreeMenuItemStorageImpl();
        LevelMenuItem first = new MenuItemImpl("Задача 1");
        LevelMenuItem second = new MenuItemImpl("Задача 1.1");

        storage.add(first, null);
        storage.add(second, first);

        ActionMenuItemFinderService service = new ActionMenuItemFinderService(storage);

        assertThat(service.findByIndex(0), is(first));
        assertThat(service.findByIndex(1), is(second));
    }
}