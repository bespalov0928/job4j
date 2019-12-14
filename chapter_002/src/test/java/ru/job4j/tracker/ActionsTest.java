package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ActionsTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void testShowAllAction() {
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        ShowAllAction act = new ShowAllAction();
        act.execute(new StubInput(new String[]{}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Name: " + item.getName() + ", Id: " + item.getId())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Test
    public void testFindByNameActionWhenFound() {
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        FindByNameAction action = new FindByNameAction();
        action.execute(new StubInput(new String[]{"fix bug"}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Name: " + item.getName() + ", Id: " + item.getId())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Test
    public void testFindByNameActionWhenNotFound() {
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        FindByNameAction action = new FindByNameAction();
        action.execute(new StubInput(new String[]{"test"}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", "")
                .add("Item not found!")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }
}
