package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.action.ExitAction;
import ru.job4j.tracker.action.FindByIdAction;
import ru.job4j.tracker.action.StubAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.ListItemRepository;
import ru.job4j.tracker.tracker.Tracker;

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
        new StartUI(input, new Tracker(new ListItemRepository()), output).init(List.of(new UserAction[]{action}));
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenPrtMenu() {
        StubInput input = new StubInput(
                new String[]{"0"}
        );
        new StartUI(input, new Tracker(new ListItemRepository()), output).init(Arrays.asList(new UserAction[]{new StubAction()}));
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("0. Stub action")
                .add("Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Ignore
    public void whenReplaceItem() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                String.valueOf(item.getId()), // id сохраненной заявки в объект tracker.
                "replaced item"
        };
        //StartUI.replaceItem(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Ignore
    public void whenDeleteItem() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {String.valueOf(item.getId())};
        //StartUI.deleteItem(new StubInput(answers), tracker);
        Item deleted = tracker.findById(item.getId());
        assertNull(deleted);
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("Name");
        tracker.add(item);
        String[] answers = {
                "0",
                String.valueOf(item.getId()),
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