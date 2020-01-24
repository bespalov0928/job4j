package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] values;
    private int index = -1;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        int nextIndex = findNextIndexOfEvenNumber();
        return nextIndex > index;
    }

    @Override
    public Integer next() {
        int nextIndex = findNextIndexOfEvenNumber();
        if (nextIndex == index) {
            throw new NoSuchElementException();
        }
        index = nextIndex;
        return values[index];
    }

    private int findNextIndexOfEvenNumber() {
        int result = index;
        for (int i = index + 1; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
