package ru.job4j.tracker.tracker;

import ru.job4j.tracker.model.Item;

import java.util.List;

public interface ITracker {
    Item add(Item item);

    Item findById(long id);

    boolean replace(long id, Item item);

    boolean delete(long id);

    List<Item> findAll();

    List<Item> findByName(String key);
}
