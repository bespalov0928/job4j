package ru.job4j.collection;

import java.util.Iterator;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;
    private int row = 0;
    private int column = 0;

    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length - 1 > row || values[row].length - 1 > column;
    }

    @Override
    public Integer next() {
        if (values[row].length == column) {
            row++;
            column = 0;
        }
        return values[row][column++];
    }
}
