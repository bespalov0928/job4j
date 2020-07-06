package ru.job4j.pooh.client.consumer;

public interface MessageConsumer {
    String consume(String request);
}
