package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.tracker.MemoryTracker;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MemoryTrackerTest {

    @Test
    public void testSortAsc() {
        MemoryTracker tracker = new MemoryTracker();
        Item item1 = new Item("Test");
        tracker.add(item1);
        Item item2 = new Item("Asc");
        tracker.add(item2);
        tracker.sortAsc();
        List<Item> result = tracker.findAll();
        List<Item> expected = List.of(item2, item1);
        assertThat(result, is(expected));
    }

    @Test
    public void testSortDesc() {
        MemoryTracker tracker = new MemoryTracker();
        Item item1 = new Item("Asc");
        tracker.add(item1);
        Item item2 = new Item("Test");
        tracker.add(item2);
        tracker.sortDesc();
        List<Item> result = tracker.findAll();
        List<Item> expected = List.of(item2, item1);
        assertThat(result, is(expected));
    }

    @Test
    public void testFindAllWhenEmpty() {
        MemoryTracker tracker = new MemoryTracker();
        List<Item> result = tracker.findAll();
        List<Item> expected = new ArrayList<>();
        assertThat(expected, is(result));
    }

    @Test
    public void testFindAllWhenNotEmpty() {
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("Test");
        tracker.add(item);
        List<Item> result = tracker.findAll();
        List<Item> expected = List.of(item);
        assertThat(expected, is(result));
    }

    @Test
    public void testFindByNameWhenItemPresent() {
        MemoryTracker tracker = new MemoryTracker();
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
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("test1");
        tracker.add(item);
        List<Item> result = tracker.findByName("Test");
        List<Item> expected = new ArrayList<>();
        assertThat(expected, is(result));
    }

    @Test
    public void testDelete() {
        MemoryTracker tracker = new MemoryTracker();
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
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("test1");
        tracker.add(item);
        boolean result = tracker.delete("11111");
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void testDeleteWhenEmpty() {
        MemoryTracker tracker = new MemoryTracker();
        boolean result = tracker.delete("11111");
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getId(), is(item.getId()));
    }

    @Test
    public void testFindItemIndexById() {
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("test1");
        tracker.add(item);
        item = new Item("test2");
        tracker.add(item);
        int result = tracker.findItemIndexById(item.getId());
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void testFindItemIndexByIdWhenNotFound() {
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("test1");
        tracker.add(item);
        int result = tracker.findItemIndexById("11111");
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void whenItemNotFound() {
        MemoryTracker tracker = new MemoryTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById("1234");
        assertNull(result);
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        MemoryTracker tracker = new MemoryTracker();
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
