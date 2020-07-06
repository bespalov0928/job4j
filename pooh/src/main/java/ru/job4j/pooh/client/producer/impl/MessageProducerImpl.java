package ru.job4j.pooh.client.producer.impl;

import ru.job4j.pooh.client.Client;
import ru.job4j.pooh.client.producer.MessageProducer;

public class MessageProducerImpl implements MessageProducer {
    private final Client client;

    public MessageProducerImpl(Client client) {
        this.client = client;
    }

    @Override
    public void produce(String request) {
        if (client.isConnected()) {
            client.sendRequest(request);
        }
    }
}
