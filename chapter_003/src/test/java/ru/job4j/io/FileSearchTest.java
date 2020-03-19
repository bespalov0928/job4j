package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileSearchTest {
    private static final String SYSTEM_TMP = System.getProperty("java.io.tmpdir");
    private static final List<String> dirs = List.of("1", File.separator + "2", File.separator + "3");
    @Before
    public void setUp() {
        StringBuilder sb = new StringBuilder(SYSTEM_TMP);
        for (var dir : dirs) {
            sb.append(dir);
            new File(sb.toString()).mkdir();
        }

        try {
            new File(SYSTEM_TMP + "1" + File.separator + "1.txt").createNewFile();
            new File(SYSTEM_TMP + String.join(File.separator, "1", "2", "2.csv")).createNewFile();
            new File(SYSTEM_TMP + String.join(File.separator, "1", "2", "3", "3.xml")).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws IOException {
        try {
            Files.walk(Path.of(SYSTEM_TMP + "1"))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReturnOnlyTxtFile() {
        List<File> result = new FileSearch().files(SYSTEM_TMP, List.of("txt"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("1.txt")
        );

        var chainedFilter = new ChainedFileFilter();

        chainedFilter.add(File::isDirectory);
        chainedFilter.add(new ExtensionFileFilter("txt"));

        result = new FileSearch().files(SYSTEM_TMP, chainedFilter);
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("1.txt")
        );
    }

    @Test
    public void whenReturnOnlyCsvFile() {
        List<File> result = new FileSearch().files(SYSTEM_TMP, List.of("csv"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("2.csv")
        );

        var chainedFilter = new ChainedFileFilter();

        chainedFilter.add(File::isDirectory);
        chainedFilter.add(new ExtensionFileFilter("csv"));

        result = new FileSearch().files(SYSTEM_TMP, chainedFilter);
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("2.csv")
        );
    }

    @Test
    public void whenReturnAllFiles() {
        List<File> result = new FileSearch().files(SYSTEM_TMP, List.of("txt", "csv", "xml"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.toList()),
                is(List.of("1.txt", "2.csv", "3.xml"))
        );

        var chainedFilter = new ChainedFileFilter();

        chainedFilter.add(File::isDirectory);
        chainedFilter.add(new ExtensionFileFilter("xml"));
        chainedFilter.add(new ExtensionFileFilter("csv"));
        chainedFilter.add(new ExtensionFileFilter("txt"));

        result = new FileSearch().files(SYSTEM_TMP, chainedFilter);
        assertThat(
                result.stream().map(File::getName).collect(Collectors.toList()),
                is(List.of("1.txt", "2.csv", "3.xml"))
        );
    }
}
