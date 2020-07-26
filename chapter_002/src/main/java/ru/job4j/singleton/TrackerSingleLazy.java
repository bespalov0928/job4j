package ru.job4j.singleton;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.ListItemRepository;
import ru.job4j.tracker.tracker.Tracker;

import java.util.List;

public class TrackerSingleLazy {
    private static TrackerSingleLazy instance;

    private Tracker tracker = new Tracker(new ListItemRepository());

    private TrackerSingleLazy() {
    }

    public static TrackerSingleLazy getInstance() {
        if (instance == null) {
            instance = new TrackerSingleLazy();
        }
        return instance;
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
