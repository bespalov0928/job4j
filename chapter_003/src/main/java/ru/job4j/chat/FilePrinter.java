package ru.job4j.chat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilePrinter extends ConsolePrinter {
    String fileName;

    public FilePrinter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void print(String output) {
        super.print(output);
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true));
            printWriter.println(output);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
