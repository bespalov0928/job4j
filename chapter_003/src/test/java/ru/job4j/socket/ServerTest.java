package ru.job4j.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private static final String LN = System.lineSeparator();

    @Test
    public void whenAskExitThenServerShouldExitAndPrintNothing() throws IOException {
        this.makeTest("exit", "");
    }

    @Test
    public void whenAskHelloThenServerShouldPrintGreeting() throws IOException {
        this.makeTest(
                String.join(LN, "hello", "exit"),
                String.join(LN, "Hello, dear friend, I'm an oracle.", LN)
        );
    }

    @Test
    public void whenUnsupportedAskThenPrintDoNotUnderstand() throws IOException {
        this.makeTest(
                String.join(LN, "unsupported", "exit"),
                String.join(LN, "I don't understand.", LN)
        );
    }

    private void makeTest(String input, String expected) throws IOException {
        var socket = mock(Socket.class);
        var in = new ByteArrayInputStream(input.getBytes());
        var out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        var server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }
}
