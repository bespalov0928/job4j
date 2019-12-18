package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    public Item findById(String id) {
        Item result = null;
        int index = findItemIndexById(id);
        if (index != -1) {
            result = this.items.get(index);
        }
        return result;
    }

    public boolean replace(String id, Item item) {
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

    public boolean delete(String id) {
        boolean result = false;
        int index = findItemIndexById(id);
        if (index > -1) {
            this.items.remove(index);
            result = true;
        }
        return result;
    }

    public List<Item> findAll() {
        return this.items;
    }

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
        Collections.sort(this.items, new ItemCompareByNameAsc());
    }

    public void sortDesc() {
        Collections.sort(this.items, new ItemCompareByNameDesc());
    }

    protected int findItemIndexById(String id) {
        int result = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
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
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }
}
