package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void testFindAllWhenEmpty() {
        Tracker tracker = new Tracker();
        Item[] result = tracker.findAll();
        Item[] expected = new Item[]{};
        assertThat(expected, is(result));
    }

    @Test
    public void testFindAllWhenNotEmpty() {
        Tracker tracker = new Tracker();
        Item item = new Item("Test");
        tracker.add(item);
        Item[] result = tracker.findAll();
        Item[] expected = new Item[]{item};
        assertThat(expected, is(result));
    }

    @Test
    public void testFindByNameWhenItemPresent() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findByName(item.getName());
        assertEquals(item, result);
    }

    @Test
    public void testFindByNameWhenItemNotFound() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findByName("Test");
        Item expected = new Item("");
        assertEquals(expected, result);
    }

    @Test
    public void testDelete() {
        Tracker tracker = new Tracker();
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
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        boolean result = tracker.delete("11111");
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void testDeleteWhenEmpty() {
        Tracker tracker = new Tracker();
        boolean result = tracker.delete("11111");
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getId(), is(item.getId()));
    }

    @Test
    public void testFindItemIndexById() {
        Tracker tracker = new Tracker();
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
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        int result = tracker.findItemIndexById("11111");
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void whenItemNotFound() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById("1234567");
        assertThat(result.getId(), is(""));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
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

    @Test
    public void testPositionWhen1ItemAdded() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        int result = tracker.getPosition();
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void testPositionWhen2ItemsAdded() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        item = new Item("test2");
        tracker.add(item);
        int result = tracker.getPosition();
        int expected = 2;
        assertEquals(expected, result);
    }

    @Test
    public void testPositionAfterDelete() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        tracker.delete(item.getId());
        int result = tracker.getPosition();
        int expected = 0;
        assertEquals(expected, result);
    }
}
