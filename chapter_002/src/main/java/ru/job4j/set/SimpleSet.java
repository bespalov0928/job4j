package ru.job4j.set;

import ru.job4j.list.SimpleList;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleList<T> items;

    public SimpleSet(int length) {
        this.items = new SimpleList<>(length);
    }

    public void add(T model) {
        if (!checkPresence(model)) {
            items.add(model);
        }
    }

    public int getSize() {
        return items.getSize();
    }

    private boolean checkPresence(T model) {
        boolean result = false;
        for (T el : items) {
            if (el.equals(model)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }
}
