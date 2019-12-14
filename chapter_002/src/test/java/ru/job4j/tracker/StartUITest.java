package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[]{"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[]{action});
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenPrtMenu() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[]{"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[]{action});
        String expect = new StringJoiner(System.lineSeparator(), System.lineSeparator(), System.lineSeparator())
                .add("0. Stub action")
                .add("Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                item.getId(), // id сохраненной заявки в объект tracker.
                "replaced item"
        };
        //StartUI.replaceItem(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {item.getId()};
        //StartUI.deleteItem(new StubInput(answers), tracker);
        Item deleted = tracker.findById(item.getId());
        assertNull(deleted);
    }
}