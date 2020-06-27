package ru.job4j.concurrent.blockingqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    @Test
    public void testSimpleBlockingQueue() throws InterruptedException {
        var actual = new ArrayList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);

        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        Thread consumer = new Thread(
                () -> {
                    while (actual.size() < 3) {
                        try {
                            actual.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        assertThat(actual, is(List.of(0, 1, 2)));
    }
}