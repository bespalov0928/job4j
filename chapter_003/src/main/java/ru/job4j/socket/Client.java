package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final Socket socket;
    private final ClientInput input;

    public Client(Socket socket, ClientInput input) {
        this.socket = socket;
        this.input = input;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String request;
        do {
            request = input.get();
            out.println(request);
            String response = in.readLine();
            while (response != null && !response.isEmpty()) {
                System.out.println("Server response: " + response);
                response = in.readLine();
            }
        } while (!"exit".equals(request));
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", Server.PORT);
            new Client(socket, new SystemInput()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
