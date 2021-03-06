package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    private TemporaryFileSystem fileSystem;

    @Before
    public void setUp() throws IOException {
        fileSystem = new TemporaryFileSystem();
        fileSystem.makeFolder("1");
        fileSystem.makeFolder("1" + File.separator + "2");
        fileSystem.makeFolder("1" + File.separator + "2" + File.separator + "3");
        fileSystem.makeFile("1", "1.txt");
        fileSystem.makeFile(String.join(File.separator, "1", "2"), "2.csv");
        fileSystem.makeFile(String.join(File.separator, "1", "2", "3"), "3.xml");
    }

    @After
    public void tearDown() throws IOException {
        fileSystem.removeRootFolder();
    }

    @Test
    public void whenReturnOnlyTxtFile() {
        List<File> result = new FileSearch().files(TemporaryFileSystem.ROOT_FOLDER, List.of("txt"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("1.txt")
        );

        var chainedFilter = new ChainedFileFilter();

        chainedFilter.add(File::isDirectory);
        chainedFilter.add(new ExtensionFileFilter("txt"));

        result = new FileSearch().files(TemporaryFileSystem.ROOT_FOLDER, chainedFilter);
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("1.txt")
        );
    }

    @Test
    public void whenReturnOnlyCsvFile() {
        List<File> result = new FileSearch().files(TemporaryFileSystem.ROOT_FOLDER, List.of("csv"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("2.csv")
        );

        var chainedFilter = new ChainedFileFilter();

        chainedFilter.add(File::isDirectory);
        chainedFilter.add(new ExtensionFileFilter("csv"));

        result = new FileSearch().files(TemporaryFileSystem.ROOT_FOLDER, chainedFilter);
        assertThat(
                result.stream().map(File::getName).collect(Collectors.joining()),
                is("2.csv")
        );
    }

    @Test
    public void whenReturnAllFiles() {
        List<File> result = new FileSearch().files(TemporaryFileSystem.ROOT_FOLDER, List.of("txt", "csv", "xml"));
        assertThat(
                result.stream().map(File::getName).collect(Collectors.toList()),
                is(List.of("1.txt", "2.csv", "3.xml"))
        );

        var chainedFilter = new ChainedFileFilter();

        chainedFilter.add(File::isDirectory);
        chainedFilter.add(new ExtensionFileFilter("xml"));
        chainedFilter.add(new ExtensionFileFilter("csv"));
        chainedFilter.add(new ExtensionFileFilter("txt"));

        result = new FileSearch().files(TemporaryFileSystem.ROOT_FOLDER, chainedFilter);
        assertThat(
                result.stream().map(File::getName).collect(Collectors.toList()),
                is(List.of("1.txt", "2.csv", "3.xml"))
        );
    }
}
