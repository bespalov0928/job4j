package ru.job4j.stack;

import ru.job4j.list.LinkedList;

public class SimpleStack<T> extends LinkedList<T> {

    public void push(T value) {
        super.add(value);
    }

    public T poll() {
        T data = super.last.data;
        super.remove(data);
        return data;
    }
}
