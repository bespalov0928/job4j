package ru.job4j.pooh.messagerepository;

import ru.job4j.pooh.message.Message;

public interface MessageRepository {
    void add(String queueName, Message message);
    Message get(String queueName);
}
