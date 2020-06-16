package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    private final char[] symbols = {'-', '\\', '|', '/', '-'};
    private int i;

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: " + symbols[i]);
                i = (i == 4 ? 0 : ++i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(6000);
        progress.interrupt();
    }
}
