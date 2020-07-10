package ru.job4j.concurrent.switcher;

public class Switcher {
    private volatile static boolean firstNotStarted = true;

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    while (true) {
                        synchronized (Switcher.class) {
                            System.out.println("Thread A");
                            if (firstNotStarted) {
                                firstNotStarted = false;
                            }
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
                            if (firstNotStarted) {
                                try {
                                    Switcher.class.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
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
        second.start();
        first.start();
        first.join();
        second.join();
    }
}
