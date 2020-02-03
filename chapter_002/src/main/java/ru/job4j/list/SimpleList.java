package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleList<T> implements Iterable<T> {
    private static final int CAPACITY_DELTA = 2;
    private Object[] items;
    private int size;
    private int modCount;

    public SimpleList(int length) {
        this.items = new Object[length];
    }

    public void add(T value) {
        modCount++;
        if (size == items.length) {
            items = Arrays.copyOf(items, items.length * CAPACITY_DELTA);
        }
        items[size++] = value;
    }

    public T get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) items[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                checkForModification();
                checkForNext();
                return (T) items[index++];
            }

            private void checkForModification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }

            private void checkForNext() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
