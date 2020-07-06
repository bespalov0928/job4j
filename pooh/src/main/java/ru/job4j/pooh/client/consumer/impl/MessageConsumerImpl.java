package ru.job4j.pooh.client.consumer.impl;

import ru.job4j.pooh.client.Client;
import ru.job4j.pooh.client.consumer.MessageConsumer;

public class MessageConsumerImpl implements MessageConsumer {
    private final Client client;

    public MessageConsumerImpl(Client client) {
        this.client = client;
    }

    @Override
    public String consume(String request) {
        if (client.isConnected()) {
            return client.sendRequest(request);
        }
        return "";
    }
}
