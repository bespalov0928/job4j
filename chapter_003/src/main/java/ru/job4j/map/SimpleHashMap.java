package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private static final int INITIAL_CAPACITY = 2;
    private Node<K, V>[] items;
    private int size;

    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public SimpleHashMap() {
        this.items = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
    }

    boolean insert(K key, V value) {
        var result = false;
        grow();
        int index = bucketIndex(key);
        if (!keyExist(index, key)) {
            items[index] = new Node<>(key, value);
            size++;
            result = true;
        }
        return result;
    }

    public V get(K key) {
        V result = null;
        int index = bucketIndex(key);
        if (keyExist(index, key)) {
            result = items[index].value;
        }
        return result;
    }

    public boolean delete(K key) {
        var result = false;
        int index = bucketIndex(key);
        if (keyExist(index, key)) {
            items[index] = null;
            size--;
            result = true;
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return items.length;
    }

    public int getInitialCapacity() {
        return INITIAL_CAPACITY;
    }

    private void grow() {
        if (items.length == size) {
            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    private int bucketIndex(K key) {
        return Math.abs(key.hashCode()) % items.length;
    }

    private boolean keyExist(int index, K key) {
        return items[index] != null && key.equals(items[index].key);
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            private Node<K, V>[] items = SimpleHashMap.this.items;
            private int index;
            private int foundCnt;

            @Override
            public boolean hasNext() {
                return foundCnt < size;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (this.items[index] == null) {
                    index++;
                }
                foundCnt++;
                return this.items[index++].value;
            }
        };
    }
}
