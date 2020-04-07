package ru.job4j.design.isp.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.isp.action.ExitAction;
import ru.job4j.design.isp.action.PrintAction;
import ru.job4j.design.isp.input.ConsoleInput;
import ru.job4j.design.isp.menu.item.LevelMenuItem;
import ru.job4j.design.isp.menu.item.MenuItemImpl;
import ru.job4j.design.isp.service.ActionMenuItemFinderService;
import ru.job4j.design.isp.status.ExitStatus;
import ru.job4j.design.isp.storage.TreeMenuItemStorage;
import ru.job4j.design.isp.storage.impl.FormattedTreeMenuItemStorage;
import ru.job4j.design.isp.storage.impl.TreeMenuItemStorageImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuManagerTest {
    private final PrintStream systemOut = System.out;
    private final InputStream systemIn = System.in;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayInputStream in = new ByteArrayInputStream("0\n1".getBytes());

    @Before
    public void setUp() {
        System.setOut(new PrintStream(out));
        System.setIn(in);
    }

    @After
    public void tearDown() {
        System.setOut(systemOut);
        System.setIn(systemIn);
    }

    @Test
    public void test() {
        var exitStatus = new ExitStatus();
        TreeMenuItemStorage<LevelMenuItem> items = new TreeMenuItemStorageImpl();
        items.add(new MenuItemImpl("Задача 1", new PrintAction("Print action have been executed.")), null);
        items.add(new MenuItemImpl("Exit System", new ExitAction(exitStatus)), null);

        new MenuManager(
                new ConsoleMenu(new FormattedTreeMenuItemStorage(items)),
                new ConsoleInput(),
                new ActionMenuItemFinderService(items), exitStatus).run();

        String expected = "Задача 1\n"
                + "Exit System\n"
                + "Print action have been executed.\n"
                + "Задача 1\n"
                + "Exit System\n";
        assertThat(new String(out.toByteArray()), is(expected));
    }
}