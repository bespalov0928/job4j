package ru.job4j.analize;

import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ServerAvailabilityAnalyzerTest {
    private static final String TARGET_FILE_PATH = "./data/unavailable.csv";

    @Test
    public void whenSourceFileNotAvailable() {
        new ServerAvailabilityAnalyzer().unavailable("", TARGET_FILE_PATH);
        assertFalse(new File(TARGET_FILE_PATH).exists());
    }

    @Test
    public void whenSourceFileIsEmpty() {
        new ServerAvailabilityAnalyzer().unavailable("./data/serverlogempty.csv", TARGET_FILE_PATH);
        assertTrue(new File(TARGET_FILE_PATH).exists());
    }

    @Test
    public void whenSourceFileNotEmpty() {
        new ServerAvailabilityAnalyzer().unavailable("./data/serverlog.csv", TARGET_FILE_PATH);
        List<String> strings = Collections.emptyList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(TARGET_FILE_PATH));
            strings = bufferedReader.lines().collect(Collectors.toList());
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(strings.get(0), is("10:58:01;10:59:01"));
        assertThat(strings.get(1), is("11:01:02;11:02:02"));
    }
}
