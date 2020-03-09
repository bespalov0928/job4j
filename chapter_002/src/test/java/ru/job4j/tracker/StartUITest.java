package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.tracker.MemoryTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream stdout = new PrintStream(out);
    private final Consumer<String> output = stdout::println;

    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[]{"0"}
        );
        StubAction action = new StubAction();
        new StartUI(input, new MemoryTracker(), output).init(List.of(new UserAction[]{action}));
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenPrtMenu() {
        StubInput input = new StubInput(
                new String[]{"0"}
        );
        new StartUI(input, new MemoryTracker(), output).init(Arrays.asList(new UserAction[]{new StubAction()}));
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("0. Stub action")
                .add("Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Ignore
    public void whenReplaceItem() {
        MemoryTracker tracker = new MemoryTracker();
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

    @Ignore
    public void whenDeleteItem() {
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {item.getId()};
        //StartUI.deleteItem(new StubInput(answers), tracker);
        Item deleted = tracker.findById(item.getId());
        assertNull(deleted);
    }

    @Test
    public void whenFindById() {
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("Name");
        tracker.add(item);
        String[] answers = {
                "0",
                item.getId(),
                "1"
        };
        new StartUI(new StubInput(answers), tracker, output).init(List.of(new FindByIdAction(output), new ExitAction()));
        String expected = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("0. Find Item by Id")
                .add("1. Exit Program")
                .add("Find Item by Id")
                .add("Name: " + item.getName() + ", Id: " + item.getId())
                .add("0. Find Item by Id")
                .add("1. Exit Program")
                .add("Exit Program")
                .toString();
        assertThat(new String(out.toByteArray()), is(expected));
    }
}