package ru.job4j.concurrent.threadpool;

import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> pool = new ArrayList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    private final class PoolThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    tasks.poll().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public ThreadPool(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
        int processorsCnt = Runtime.getRuntime().availableProcessors();
        while (--processorsCnt > -1) {
            pool.add(new PoolThread());
        }
        pool.forEach(Thread::start);
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        pool.forEach(Thread::interrupt);
    }
}
