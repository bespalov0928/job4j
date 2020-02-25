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

public class ClientTest {
    private static final String LN = System.lineSeparator();

    @Test
    public void whenInputExitThenClientShouldSendExitAndExit() throws IOException {
        this.makeTest("exit", String.join("", "exit", LN), LN);
    }

    @Test
    public void whenSendInputSequentially() throws IOException {
        this.makeTest("hello", String.join("", "hello", LN, "exit", LN), LN);
    }

    private void makeTest(String input, String expected, String response) throws IOException {
        var socket = mock(Socket.class);
        var clientInput = mock(ClientInput.class);
        var in = new ByteArrayInputStream(response.getBytes());
        var out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        when(clientInput.get()).thenReturn(input, "exit");
        var client = new Client(socket, clientInput);
        client.start();
        assertThat(out.toString(), is(expected));
    }
}
