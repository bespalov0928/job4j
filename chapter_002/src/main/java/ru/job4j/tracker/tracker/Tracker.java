package ru.job4j.tracker.tracker;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.IItemRepository;

import java.util.List;

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
    public Item findById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean replace(long id, Item item) {
        return repository.replace(id, item);
    }

    @Override
    public boolean delete(long id) {
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
