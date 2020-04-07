package ru.job4j.design.isp.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleInputTest {
    private final InputStream systemIn = System.in;

    @Before
    public void setUp() {
        System.setIn(new ByteArrayInputStream("Test input".getBytes()));
    }

    @After
    public void tearDown() {
        System.setIn(systemIn);
    }

    @Test
    public void whenGetShouldReturnCorrectInput() {
        Input input = new ConsoleInput();
        assertThat(input.get(), is("Test input"));
    }
}