package ru.job4j.stack;

import ru.job4j.list.LinkedList;

public class SimpleStack<T> {

    private LinkedList<T> items = new LinkedList<>();

    public void push(T value) {
        items.add(value);
    }

    public T poll() {
        return items.removeLast();
    }

    public int getSize() {
        return items.getSize();
    }
}
