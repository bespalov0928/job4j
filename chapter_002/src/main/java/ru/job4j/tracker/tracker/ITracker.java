package ru.job4j.tracker.tracker;

import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;

public interface ITracker {
    Item add(Item item);

    Item findById(String id);

    boolean replace(String id, Item item);

    boolean delete(String id);

    List<Item> findAll();

    List<Item> findByName(String key);
}
