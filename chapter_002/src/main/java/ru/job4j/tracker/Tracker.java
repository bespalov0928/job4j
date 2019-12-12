package ru.job4j.tracker;

import java.util.Random;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    public Item findById(String id) {
        Item result = new Item("");
        int index = findItemIndexById(id);
        if (index != -1) {
            result = this.items[index];
        }
        return result;
    }

    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = findItemIndexById(id);
        if (index > -1) {
            item.setId(items[index].getId());
            items[index] = item;
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        int index = findItemIndexById(id);
        if (index > -1) {
            System.arraycopy(this.items, index + 1, this.items, index, this.position - index);
            result = true;
            this.position--;
        }
        return result;
    }

    public Item[] findAll() {
        Item[] result = new Item[this.position];
        System.arraycopy(this.items, 0, result, 0, this.position);
        return result;
    }

    public Item[] findByName(String key) {
        Item[] found = new Item[this.position];
        int count = 0;
        for (Item item : this.items) {
            if (item == null) {
                break;
            }
            if (item.getName().equals(key)) {
                found[count++] = item;
            }
        }
        Item[] result = new Item[0];
        if (count > 0) {
            result = new Item[count];
            System.arraycopy(found, 0, result, 0, count);
        }

        return result;
    }

    protected int findItemIndexById(String id) {
        int result = -1;
        for (int index = 0; index < items.length && items[index] != null; index++) {
            if (items[index].getId().equals(id)) {
                result = index;
                break;
            }
        }
        return result;
    }

    protected int getPosition() {
        return this.position;
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
