package ru.job4j.design.isp.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.isp.menu.item.LevelMenuItem;
import ru.job4j.design.isp.menu.item.MenuItemImpl;
import ru.job4j.design.isp.storage.TreeMenuItemStorage;
import ru.job4j.design.isp.storage.impl.TreeMenuItemStorageImpl;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleMenuTest {
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void tearDown() {
        System.setOut(systemOut);
    }

    @Test
    public void whenDisplayShouldPrintCorrectMenu() {
        TreeMenuItemStorage<LevelMenuItem> items = new TreeMenuItemStorageImpl();
        items.add(new MenuItemImpl("Задача 1"), null);
        items.add(new MenuItemImpl("Задача 1.1"), new MenuItemImpl("Задача 1"));
        items.add(new MenuItemImpl("Задача 1.1.1"), new MenuItemImpl("Задача 1.1"));
        items.add(new MenuItemImpl("Задача 1.1.2"), new MenuItemImpl("Задача 1.1"));
        items.add(new MenuItemImpl("Задача 1.2"), new MenuItemImpl("Задача 1"));
        items.add(new MenuItemImpl("Задача 2"), null);
        items.add(new MenuItemImpl("Задача 2.1"), new MenuItemImpl("Задача 2"));
        var menu = new ConsoleMenu(items);

        menu.display();

        String expected = "Задача 1\n"
                + "Задача 1.1\n"
                + "Задача 1.1.1\n"
                + "Задача 1.1.2\n"
                + "Задача 1.2\n"
                + "Задача 2\n"
                + "Задача 2.1\n";
        assertThat(new String(out.toByteArray()), is(expected));
    }
}