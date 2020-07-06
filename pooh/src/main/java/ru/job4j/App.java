package ru.job4j;

import ru.job4j.pooh.client.consumer.impl.MessageConsumerImpl;
import ru.job4j.pooh.client.impl.SocketClient;
import ru.job4j.pooh.client.producer.impl.MessageProducerImpl;
import ru.job4j.pooh.messagebroker.impl.SimpleMessageBroker;
import ru.job4j.pooh.messagerepository.impl.InMemoryConcurrentMessageRepository;
import ru.job4j.pooh.parser.message.JSonMessageParser;
import ru.job4j.pooh.parser.request.HttpRequestParser;
import ru.job4j.pooh.requesthandler.impl.HttpMessageRequestHandlerFactory;
import ru.job4j.pooh.server.impl.MultiThreadedMessagingSocketServer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class App {
    private final static String POST_MESSAGE_TMPL = "POST /queue {\"queue\":\"weather\",\"text\":\"temperature +%s C\"}";
    private final static String GET_MESSAGE_TMPL = "GET /queue/weather";

    private final static Executor THREAD_POOL = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        new Thread(() -> new MultiThreadedMessagingSocketServer(
                new HttpRequestParser(),
                new HttpMessageRequestHandlerFactory(),
                new JSonMessageParser(),
                new SimpleMessageBroker(new InMemoryConcurrentMessageRepository())
        ).start()).start();

        THREAD_POOL.execute(() -> IntStream.range(0, 1000)
                .forEach(i -> new MessageProducerImpl(new SocketClient("localhost", 5000))
                        .produce(String.format(POST_MESSAGE_TMPL, i))
                ));

        THREAD_POOL.execute(() -> IntStream.range(0, 1000)
                .forEach(i -> System.out.println(new MessageConsumerImpl(new SocketClient("localhost", 5000))
                        .consume(GET_MESSAGE_TMPL))
                ));
    }
}
