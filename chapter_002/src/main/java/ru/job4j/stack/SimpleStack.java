package ru.job4j.stack;

import ru.job4j.list.LinkedList;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class SimpleStack<T> extends LinkedList<T> {

    public void push(T value) {
        super.add(value);
    }

    public T poll() {
        T data;
        try {
            data = super.removeLast();
        } catch (NoSuchElementException e) {
            throw new EmptyStackException();
        }
        return data;
    }
}
