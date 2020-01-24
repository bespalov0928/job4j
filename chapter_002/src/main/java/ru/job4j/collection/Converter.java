package ru.job4j.collection;

import java.util.Collections;
import java.util.Iterator;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> currentIt = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                boolean result = currentIt.hasNext();
                if (!result) {
                    while (it.hasNext()) {
                        currentIt = it.next();
                        if (currentIt.hasNext()) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!currentIt.hasNext()) {
                    currentIt = it.next();
                }
                return currentIt.next();
            }
        };
    }
}
