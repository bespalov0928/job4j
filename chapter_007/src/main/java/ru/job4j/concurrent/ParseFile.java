package ru.job4j.concurrent;

import java.io.*;
import java.util.stream.Collectors;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        synchronized (file) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return reader.lines().collect(Collectors.joining());
            }
        }
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContent().replaceAll("\\P{InBasic_Latin}", "");
    }

    public void saveContent(String content) throws IOException {
        synchronized (file) {
            try (var bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(content);
            }
        }
    }
}
