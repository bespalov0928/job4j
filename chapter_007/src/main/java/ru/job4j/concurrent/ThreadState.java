package ru.job4j.concurrent;

public class ThreadState {
    private static void printInfo(Thread thread) {
        System.out.println(thread.getName() + " " + thread.getState());
    }

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }
        );
        Thread second = new Thread(
                () -> { }
        );
        printInfo(first);
        printInfo(second);
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            printInfo(first);
            printInfo(second);
        }
        printInfo(first);
        printInfo(second);
    }
}
