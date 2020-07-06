package ru.job4j.pooh.parser.message;

import com.google.gson.Gson;
import ru.job4j.pooh.message.Message;

public class JSonMessageParser implements MessageParser {
    @Override
    public Message jsonToMessage(String json) {
        return new Gson().fromJson(json, Message.class);
    }

    @Override
    public String messageToJson(Message message) {
        return new Gson().toJson(message);
    }
}
