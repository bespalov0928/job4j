package ru.job4j.pooh.requesthandler;

import ru.job4j.pooh.messagebroker.MessageBroker;
import ru.job4j.pooh.parser.message.MessageParser;
import ru.job4j.pooh.parser.request.RequestParser;

import java.net.Socket;

public interface RequestHandlerFactory {
    MessageRequestHandler build(Socket request, RequestParser parser, MessageBroker broker, MessageParser messageParser);
}
