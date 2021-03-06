package ru.job4j.singleton;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.ListItemRepository;
import ru.job4j.tracker.tracker.Tracker;

import java.util.List;

public enum TrackerSingleEnum {
    INSTANCE;

    private Tracker tracker = new Tracker(new ListItemRepository());

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(long id) {
        return tracker.findById(id);
    }

    public boolean replace(long id, Item item) {
        return tracker.replace(id, item);
    }

    public boolean delete(long id) {
        return tracker.delete(id);
    }

    public List<Item> findAll() {
        return tracker.findAll();
    }

    public List<Item> findByName(String key) {
        return tracker.findByName(key);
    }
}
