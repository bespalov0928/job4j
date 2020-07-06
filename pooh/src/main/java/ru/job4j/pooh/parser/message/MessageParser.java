package ru.job4j.pooh.parser.message;

import ru.job4j.pooh.message.Message;

public interface MessageParser {
    Message jsonToMessage(String json);

    String messageToJson(Message message);
}
