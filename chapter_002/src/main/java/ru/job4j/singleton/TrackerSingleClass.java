package ru.job4j.singleton;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.tracker.MemoryTracker;

import java.util.List;

public class TrackerSingleClass {

    private MemoryTracker tracker = new MemoryTracker();

    private TrackerSingleClass() {
    }

    private static final class Holder {
        private static final TrackerSingleClass INSTANCE = new TrackerSingleClass();
    }

    public static TrackerSingleClass getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(String id) {
        return tracker.findById(id);
    }

    public boolean replace(String id, Item item) {
        return tracker.replace(id, item);
    }

    public boolean delete(String id) {
        return tracker.delete(id);
    }

    public List<Item> findAll() {
        return tracker.findAll();
    }

    public List<Item> findByName(String key) {
        return tracker.findByName(key);
    }
}
