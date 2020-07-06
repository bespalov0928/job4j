package ru.job4j.pooh.client.impl;

import ru.job4j.pooh.client.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient implements Client {
    private Socket socket;
    boolean isConnected = true;

    public SocketClient(String host, int port) {
        try {
            socket = new Socket(InetAddress.getByName(host), port);
        } catch (IOException e) {
            isConnected = false;
        }
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public String sendRequest(String request) {
        String response = null;
        try (var out = new PrintWriter(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println(request);
            out.flush();
            while ((response = in.readLine()) != null && in.ready()) {
                response += in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
