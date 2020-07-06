package ru.job4j.concurrent.switcher;

public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    while (true) {
                        synchronized (Switcher.class) {
                            System.out.println("Thread A");
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
