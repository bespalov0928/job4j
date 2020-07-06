package ru.job4j.pooh.server.impl;

import ru.job4j.pooh.messagebroker.MessageBroker;
import ru.job4j.pooh.parser.message.MessageParser;
import ru.job4j.pooh.parser.request.RequestParser;
import ru.job4j.pooh.requesthandler.RequestHandlerFactory;
import ru.job4j.pooh.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class MultiThreadedMessagingSocketServer implements Server {
    private final static int SERVER_PORT = 5000;
    private final ExecutorService threadPool;

    private final RequestParser requestParser;
    private final RequestHandlerFactory handlerFactory;
    private final MessageParser messageParser;
    private final MessageBroker broker;

    public MultiThreadedMessagingSocketServer(
            RequestParser requestParser,
            RequestHandlerFactory handlerFactory,
            MessageParser messageParser, MessageBroker broker) {
        this.requestParser = requestParser;
        this.handlerFactory = handlerFactory;
        this.messageParser = messageParser;
        this.broker = broker;
        this.threadPool = newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void start() {
        try (var serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Broker server started." + System.lineSeparator() + "Listening for connections on port: " + SERVER_PORT);
            while (true) {
                var request = serverSocket.accept();
                System.out.println("Request received... Trying to handle request...");
                threadPool.execute(() -> handlerFactory.build(request, requestParser, broker, messageParser).handle());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
