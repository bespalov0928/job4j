package ru.job4j.tracker.tracker;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.ItemCompareByNameAsc;
import ru.job4j.tracker.ItemCompareByNameDesc;
import ru.job4j.tracker.repository.IItemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tracker implements ITracker {
    private IItemRepository repository;

    public Tracker(IItemRepository repository) {
        this.repository = repository;
    }


    @Override
    public Item add(Item item) {
        return repository.add(item);
    }

    @Override
    public Item findById(String id) {
        return repository.findById(id);
    }

    @Override
    public boolean replace(String id, Item item) {
        return repository.replace(id, item);
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Item> findByName(String key) {
        return repository.findByName(key);
    }
}
