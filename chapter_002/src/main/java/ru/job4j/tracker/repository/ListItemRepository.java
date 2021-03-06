package ru.job4j.tracker.repository;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.ItemCompareByNameAsc;
import ru.job4j.tracker.ItemCompareByNameDesc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListItemRepository implements IItemRepository {
    private static long id = 0;
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    @Override
    public Item findById(long id) {
        Item result = null;
        int index = findItemIndexById(id);
        if (index != -1) {
            result = this.items.get(index);
        }
        return result;
    }

    @Override
    public boolean replace(long id, Item item) {
        boolean result = false;
        int index = findItemIndexById(id);
        if (index > -1) {
            item.setId(items.get(index).getId());
            items.remove(index);
            items.add(index, item);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        int index = findItemIndexById(id);
        if (index > -1) {
            this.items.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        return this.items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> found = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                found.add(item);
            }
        }
        return found;
    }

    public void sortAsc() {
        this.items.sort(new ItemCompareByNameAsc());
    }

    public void sortDesc() {
        this.items.sort(new ItemCompareByNameDesc());
    }

    public int findItemIndexById(long id) {
        int result = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                result = index;
                break;
            }
        }
        return result;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private long generateId() {
        return id++;
    }
}
