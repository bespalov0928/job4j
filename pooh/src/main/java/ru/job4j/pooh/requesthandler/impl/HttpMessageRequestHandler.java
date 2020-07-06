package ru.job4j.pooh.requesthandler.impl;

import ru.job4j.pooh.message.Message;
import ru.job4j.pooh.messagebroker.MessageBroker;
import ru.job4j.pooh.parser.message.MessageParser;
import ru.job4j.pooh.parser.request.RequestParser;
import ru.job4j.pooh.request.HttpRequest;
import ru.job4j.pooh.requesthandler.MessageRequestHandler;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

import static ru.job4j.pooh.request.HttpRequestMethod.POST;

public class HttpMessageRequestHandler implements MessageRequestHandler {
    private final Socket client;
    private final RequestParser parser;
    private final MessageParser messageParser;
    private final MessageBroker broker;

    public HttpMessageRequestHandler(Socket client, RequestParser parser, MessageParser messageParser, MessageBroker broker) {
        this.client = client;
        this.parser = parser;
        this.messageParser = messageParser;
        this.broker = broker;
    }

    @Override
    public void handle() {
        try (PrintWriter out = new PrintWriter(client.getOutputStream(), false);
             InputStream in = client.getInputStream()) {
            HttpRequest request = parser.parse(in);
            if (POST.equals(request.getMethod())) {
                postMessage(request);
            } else {
                var json = getMessage(request);
                out.println(json);
            }
            out.flush();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postMessage(HttpRequest request) {
        var message = messageParser.jsonToMessage(request.getBody());
        String queueName = getQueueNameFromRequestUrl(request.getRequestUrl());
        queueName += Objects.nonNull(message.getTopic()) ? message.getTopic() : message.getQueue();
        broker.postMessage(queueName, message);
    }

    private String getMessage(HttpRequest request) {
        String queueName = getQueueNameFromRequestUrl(request.getRequestUrl());
        Message message = broker.getMessage(queueName);
        return Objects.nonNull(message) ? messageParser.messageToJson(message) : "";
    }

    private String getQueueNameFromRequestUrl(String requestUrl) {
        return requestUrl.replace("/", "");
    }
}
