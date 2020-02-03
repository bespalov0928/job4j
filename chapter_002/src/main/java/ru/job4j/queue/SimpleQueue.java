package ru.job4j.queue;

import ru.job4j.stack.SimpleStack;

public class SimpleQueue<T> {
    SimpleStack<T> pushStack = new SimpleStack<>();
    SimpleStack<T> pollStack = new SimpleStack<>();

    public void push(T value) {
        pushStack.push(value);
    }

    public T poll() {
        T result;
        if (pollStack.getSize() == 0) {
            while (pushStack.getSize() != 0) {
                pollStack.push(pushStack.poll());
            }
        }
        result = pollStack.poll();
        return result;
    }
}
