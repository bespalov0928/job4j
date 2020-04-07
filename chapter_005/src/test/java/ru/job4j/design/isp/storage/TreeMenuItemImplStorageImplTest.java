package ru.job4j.design.isp.storage;

import org.junit.Test;
import ru.job4j.design.isp.menu.item.LevelMenuItem;
import ru.job4j.design.isp.menu.item.MenuItemImpl;
import ru.job4j.design.isp.storage.impl.TreeMenuItemStorageImpl;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class TreeMenuItemImplStorageImplTest {

    @Test
    public void whenStorageIsEmptyThanIteratorHasNextShouldReturnFalse() {
        TreeMenuItemStorage<LevelMenuItem> storage = new TreeMenuItemStorageImpl();
        assertFalse(storage.iterator().hasNext());
    }

    @Test
    public void whenStorageHaveItemThanIteratorNextShouldReturnThisItem() {
        TreeMenuItemStorage<LevelMenuItem> storage = new TreeMenuItemStorageImpl();
        var item = new MenuItemImpl("Test");
        storage.add(item, null);
        Iterator<LevelMenuItem> it = storage.iterator();

        assertThat("Test", is(it.next().getTitle()));
    }

    @Test
    public void whenStorageHaveItemsThanIteratorNextShouldReturnThemInCorrectOrder() {
        TreeMenuItemStorage<LevelMenuItem> storage = new TreeMenuItemStorageImpl();
        storage.add(new MenuItemImpl("Задача 1"), null);
        storage.add(new MenuItemImpl("Задача 1.1"), new MenuItemImpl("Задача 1"));
        storage.add(new MenuItemImpl("Задача 1.2"), new MenuItemImpl("Задача 1"));
        storage.add(new MenuItemImpl("Задача 2"), null);
        storage.add(new MenuItemImpl("Задача 2.1"), new MenuItemImpl("Задача 2"));

        Iterator<LevelMenuItem> it = storage.iterator();

        assertThat("Задача 1", is(it.next().getTitle()));
        assertThat("Задача 1.1", is(it.next().getTitle()));
        assertThat("Задача 1.2", is(it.next().getTitle()));
        assertThat("Задача 2", is(it.next().getTitle()));
        assertThat("Задача 2.1", is(it.next().getTitle()));
        assertFalse(it.hasNext());
    }
}