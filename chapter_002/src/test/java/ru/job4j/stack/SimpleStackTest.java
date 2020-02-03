package ru.job4j.stack;

import org.junit.Test;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    @Test
    public void whenPollElementsShouldBeReturnedInLIFOOrder() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPollShouldThrowExceptionIfEmpty() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.poll();
    }
}
