package ru.job4j.singleton;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.ListItemRepository;
import ru.job4j.tracker.tracker.Tracker;

import java.util.List;

public class TrackerSingleClass {

    private Tracker tracker = new Tracker(new ListItemRepository());

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
        return tracker.findById(Long.parseLong(id));
    }

    public boolean replace(String id, Item item) {
        return tracker.replace(Long.parseLong(id), item);
    }

    public boolean delete(String id) {
        return tracker.delete(Long.parseLong(id));
    }

    public List<Item> findAll() {
        return tracker.findAll();
    }

    public List<Item> findByName(String key) {
        return tracker.findByName(key);
    }
}
