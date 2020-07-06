package ru.job4j.pooh.messagebroker.impl;

import ru.job4j.pooh.message.Message;
import ru.job4j.pooh.messagebroker.MessageBroker;
import ru.job4j.pooh.messagerepository.MessageRepository;

public class SimpleMessageBroker implements MessageBroker {
    private final MessageRepository messageRepository;

    public SimpleMessageBroker(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void postMessage(String queueName, Message message) {
        messageRepository.add(queueName, message);
    }

    @Override
    public Message getMessage(String queueName) {
        return messageRepository.get(queueName);
    }
}
