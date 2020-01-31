package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private int size;
    private int modCount;
    protected Node<T> first;
    protected Node<T> last;

    protected static class Node<T> {
        public T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public void add(T value) {
        Node<T> newEl = new Node<>(value);
        if (size == 0) {
            first = newEl;
        } else {
            last.next = newEl;
        }
        last = newEl;
        size++;
        modCount++;
    }

    public T get(int index) {
        T result = null;
        if (first == null) {
            throw new IndexOutOfBoundsException();
        }
        for (Node<T> curr = first; curr != null; curr = curr.next) {
            if (index-- == 0) {
                result = curr.data;
                break;
            }
        }
        return result;
    }

    public void remove(T value) {
        if (first == null) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> prev = null;
        for (Node<T> curr = first; curr != null; curr = curr.next) {
            if (value.equals(curr.data)) {
                unlink(prev, curr);
                break;
            }
            prev = curr;
        }
    }

    public T removeFirst() {
        T result = null;
        if (first != null) {
            result = first.data;
            unlink(null, first);
        }
        return result;
    }

    public T removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        T result = last.data;
        remove(result);
        return result;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private Node<T> curr = first;

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

    private void unlink(Node<T> prev, Node<T> curr) {
        if (curr == first) {
            if (last == first) {
                last = null;
                first = null;
            } else {
                first = first.next;
            }
        } else if (curr == last) {
            prev.next = null;
            last = prev;
        } else {
            prev.next = curr.next;
        }
        size--;
        modCount++;
    }
}
