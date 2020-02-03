package ru.job4j.queue;

import org.junit.Test;
import ru.job4j.stack.SimpleStack;

import java.util.EmptyStackException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    @Test
    public void whenPollElementsShouldBeReturnedInFIFOOrder() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }

    @Test
    public void whenPollShouldReturnNullIfQueueIsEmpty() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        assertNull(queue.poll());
    }
}