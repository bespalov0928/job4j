package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node first;

    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public void add(T value) {
        modCount++;
        size++;
        Node newEl = new Node(value);
        newEl.next = first;
        first = newEl;
    }

    public T get(int index) {
        T result = null;
        if (first == null) {
            throw new IndexOutOfBoundsException();
        }
        for (Node curr = first; curr != null; curr = curr.next) {
            if (index-- == 0) {
                result = curr.data;
                break;
            }
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private Node curr = first;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                checkForModification();
                checkForNext();
                T data = curr.data;
                curr = curr.next;
                return data;
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
