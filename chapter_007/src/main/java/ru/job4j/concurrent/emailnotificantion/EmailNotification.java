package ru.job4j.concurrent.emailnotificantion;

import java.util.concurrent.ExecutorService;

import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class EmailNotification {
    private final ExecutorService pool;

    public EmailNotification() {
        this.pool = newFixedThreadPool(getRuntime().availableProcessors());
    }

    public void emailTo(User user) {
        String subject = format("Notification %s to email %s", user.getUsername(), user.getEmail());
        String body = format("Add a new event to %s", user.getUsername());

        pool.submit(() -> send(subject, body, user.getEmail()));
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
