package ru.job4j.concurrent.switcher;

public class Switcher {
    private volatile static boolean firstNotStarted = true;

    private static void threadPrint(String message) throws InterruptedException {
        System.out.println(message);
        Switcher.class.notify();
        Switcher.class.wait();
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    synchronized (Switcher.class) {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                if (firstNotStarted) {
                                    firstNotStarted = false;
                                }
                                threadPrint("Thread A");
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    synchronized (Switcher.class) {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                if (firstNotStarted) {
                                    Switcher.class.wait();
                                }
                                threadPrint("Thread B");
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
        );
        second.start();
        first.start();
        first.join();
        second.join();
    }
}
