package ru.job4j.collection;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> currentIt = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                boolean result = currentIt.hasNext();
                while (!result && it.hasNext()) {
                    currentIt = it.next();
                    if (currentIt.hasNext()) {
                        result = true;
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return currentIt.next();
                }
                throw new NoSuchElementException();
            }
        };
    }
}
