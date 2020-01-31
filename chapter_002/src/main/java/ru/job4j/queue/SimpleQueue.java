package ru.job4j.queue;

import ru.job4j.stack.SimpleStack;

public class SimpleQueue<T> extends SimpleStack<T> {
    @Override
    public T poll() {
        return super.removeFirst();
    }
}
