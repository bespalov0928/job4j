package ru.job4j.pooh.messagebroker;

import ru.job4j.pooh.message.Message;

public interface MessageBroker {
    void postMessage(String queueName, Message message);

    Message getMessage(String queueName);
}
