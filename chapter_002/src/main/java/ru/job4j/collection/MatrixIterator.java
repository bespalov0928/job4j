package ru.job4j.collection;

import java.util.Iterator;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;
    private int arrayIndex = 0;
    private int elementIndex = 0;

    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length - 1 > arrayIndex || values[arrayIndex].length - 1 > elementIndex;
    }

    @Override
    public Integer next() {
        if (values[arrayIndex].length == elementIndex) {
            arrayIndex++;
            elementIndex = 0;
        }
        return values[arrayIndex][elementIndex++];
    }
}
