package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileSearchTest {
    private String path = System.getProperty("java.io.tmpdir") + "filesearchtest";

    @Before
    public void setUp() {
        new File(path + "/1/2/3").mkdirs();
        try {
            new File(path + "/1/1.txt").createNewFile();
            new File(path + "/1/2/2.csv").createNewFile();
            new File(path + "/1/2/3/3.xml").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReturnOnlyTxtFile() {
        List<File> result = new FileSearch().files(path, List.of("txt"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("1.txt")
        );
    }

    @Test
    public void whenReturnOnlyCsvFile() {
        List<File> result = new FileSearch().files(path, List.of("csv"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("2.csv")
        );
    }

    @Test
    public void whenReturnAllFiles() {
        List<File> result = new FileSearch().files(path, List.of("txt", "csv", "xml"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.toList()),
                is(List.of("1.txt", "2.csv", "3.xml"))
        );
    }
}
