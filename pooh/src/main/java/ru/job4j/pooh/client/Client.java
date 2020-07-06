package ru.job4j.pooh.client;

public interface Client {
    boolean isConnected();

    String sendRequest(String request);
}
