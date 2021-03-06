package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.ListItemRepository;
import ru.job4j.tracker.tracker.Tracker;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerTest {

    @Test
    @Ignore
    public void testSortAsc() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item1 = new Item("Test");
        tracker.add(item1);
        Item item2 = new Item("Asc");
        tracker.add(item2);
        //tracker.sortAsc();
        List<Item> result = tracker.findAll();
        List<Item> expected = List.of(item2, item1);
        assertThat(result, is(expected));
    }

    @Test
    @Ignore
    public void testSortDesc() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item1 = new Item("Asc");
        tracker.add(item1);
        Item item2 = new Item("Test");
        tracker.add(item2);
        //tracker.sortDesc();
        List<Item> result = tracker.findAll();
        List<Item> expected = List.of(item2, item1);
        assertThat(result, is(expected));
    }

    @Test
    public void testFindAllWhenEmpty() {
        Tracker tracker = new Tracker(new ListItemRepository());
        List<Item> result = tracker.findAll();
        List<Item> expected = new ArrayList<>();
        assertThat(expected, is(result));
    }

    @Test
    public void testFindAllWhenNotEmpty() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("Test");
        tracker.add(item);
        List<Item> result = tracker.findAll();
        List<Item> expected = List.of(item);
        assertThat(expected, is(result));
    }

    @Test
    public void testFindByNameWhenItemPresent() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item1 = new Item("test1");
        tracker.add(item1);
        Item item2 = new Item("test1");
        tracker.add(item2);
        List<Item> result = tracker.findByName(item1.getName());
        List<Item> expected = List.of(item1, item2);
        assertThat(result, is(expected));
    }

    @Test
    public void testFindByNameWhenItemNotFound() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("test1");
        tracker.add(item);
        List<Item> result = tracker.findByName("Test");
        List<Item> expected = new ArrayList<>();
        assertThat(expected, is(result));
    }

    @Test
    public void testDelete() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("test1");
        tracker.add(item);
        item = new Item("test2");
        tracker.add(item);
        boolean result = tracker.delete(item.getId());
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void testDeleteWhenNotExist() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("test1");
        tracker.add(item);
        boolean result = tracker.delete(Long.parseLong("11111"));
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void testDeleteWhenEmpty() {
        Tracker tracker = new Tracker(new ListItemRepository());
        boolean result = tracker.delete(Long.parseLong("11111"));
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getId(), is(item.getId()));
    }

    @Test
    @Ignore
    public void testFindItemIndexById() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("test1");
        tracker.add(item);
        item = new Item("test2");
        tracker.add(item);
        //int result = tracker.findItemIndexById(item.getId());
        int expected = 1;
        //assertEquals(expected, result);
    }

    @Test
    @Ignore
    public void testFindItemIndexByIdWhenNotFound() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("test1");
        tracker.add(item);
        //int result = tracker.findItemIndexById("11111");
        int expected = -1;
        //assertEquals(expected, result);
    }

    @Test
    public void whenItemNotFound() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(Long.parseLong("1234"));
        assertNull(result);
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker(new ListItemRepository());
        Item previous = new Item("test1");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        Item found = tracker.findById(previous.getId());
        assertThat(found.getName(), is(next.getName()));
    }
}
