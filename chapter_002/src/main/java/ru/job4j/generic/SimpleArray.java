package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] values;
    private int size = 0;

    public SimpleArray(int length) {
        this.values = (T[]) new Object[length];
    }

    public void add(T model) {
        Objects.checkIndex(size, values.length);
        values[size++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        values[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        if (index != size - 1) {
            System.arraycopy(values, index + 1, values, index, size - index - 1);
        }
        values[--size] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return values[index];
    }

    public int length() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (index >= size) {
                    throw new NoSuchElementException();
                }
                return values[index++];
            }
        };
    }
}
