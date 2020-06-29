package ru.job4j.concurrent.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final int size;
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.offer(value);
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        T t = queue.poll();
        notify();
        return t;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}