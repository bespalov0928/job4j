package ru.job4j.concurrent.switcher;

public class Switcher {
    private volatile static boolean firstNotStarted = true;

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    while (true) {
                        synchronized (Switcher.class) {
                            System.out.println("Thread A");
                            firstNotStarted = false;
                            Switcher.class.notify();
                            try {
                                Switcher.class.wait();
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        if (firstNotStarted) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            continue;
                        }
                        synchronized (Switcher.class) {
                            System.out.println("Thread B");
                            Switcher.class.notify();
                            try {
                                Switcher.class.wait();
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
