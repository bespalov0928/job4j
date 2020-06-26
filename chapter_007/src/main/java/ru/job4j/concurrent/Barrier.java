package ru.job4j.concurrent;

public class Barrier {
    private volatile boolean flag = false;

    private final Object monitor = this;

    public void on() {
        System.out.println(Thread.currentThread().getName() + " invoked on");
        synchronized (monitor) {
            flag = true;
            System.out.println(Thread.currentThread().getName() + " try to notify");
            monitor.notifyAll();
        }
    }

    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    public void check() {
        System.out.println(Thread.currentThread().getName() + " invoked check");
        synchronized (monitor) {
            while (!flag) {
                try {
                    System.out.println(Thread.currentThread().getName() + " trying to wait");
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
