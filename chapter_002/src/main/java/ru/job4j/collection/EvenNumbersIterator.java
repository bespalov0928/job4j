package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] values;
    private int index = -1;
    private int nextIndex = -1;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        setNextIndexOfEvenNumber();
        return nextIndex > index;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = nextIndex;
        return values[index];
    }

    private void setNextIndexOfEvenNumber() {
        for (int i = index + 1; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                nextIndex = i;
                break;
            }
        }
    }
}
