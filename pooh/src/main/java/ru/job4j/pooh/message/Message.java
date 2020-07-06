package ru.job4j.pooh.message;

public class Message {
    private String queue;
    private String topic;
    private String text;

    public Message() {
    }

    public Message(String queue, String topic, String text) {
        this.queue = queue;
        this.topic = topic;
        this.text = text;
    }

    public String getQueue() {
        return queue;
    }

    public String getTopic() {
        return topic;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{"
                + "queue='" + queue + '\''
                + ", topic='" + topic + '\''
                + ", message='" + text + '\'' + '}';
    }
}
