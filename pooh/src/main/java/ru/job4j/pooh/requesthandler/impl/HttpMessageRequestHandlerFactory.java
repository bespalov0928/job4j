package ru.job4j.pooh.requesthandler.impl;

import ru.job4j.pooh.messagebroker.MessageBroker;
import ru.job4j.pooh.parser.message.MessageParser;
import ru.job4j.pooh.parser.request.RequestParser;
import ru.job4j.pooh.requesthandler.MessageRequestHandler;
import ru.job4j.pooh.requesthandler.RequestHandlerFactory;

import java.net.Socket;

public class HttpMessageRequestHandlerFactory implements RequestHandlerFactory {
    @Override
    public MessageRequestHandler build(Socket request, RequestParser parser, MessageBroker broker, MessageParser messageParser) {
        return new HttpMessageRequestHandler(request, parser, messageParser, broker);
    }
}
