package ru.job4j.pooh.messagerepository.impl;

import ru.job4j.pooh.message.Message;
import ru.job4j.pooh.messagerepository.MessageRepository;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryConcurrentMessageRepository implements MessageRepository {
    private final Map<String, Queue<Message>> messages = new ConcurrentHashMap<>();

    @Override
    public void add(String queueName, Message message) {
        messages.putIfAbsent(queueName, new ConcurrentLinkedQueue<>());
        messages.get(queueName).offer(message);
    }

    @Override
    public Message get(String queueName) {
        var queue = messages.get(queueName);
        return Objects.nonNull(queue) ? queue.poll() : null;
    }
}
