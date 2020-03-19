package ru.job4j.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.io.TemporaryFileSystem;

import java.io.*;

import static org.junit.Assert.*;

public class FileSearchTest {
    private TemporaryFileSystem fileSystem;
    private String resultFile;

    @Before
    public void setUp() throws IOException {
        resultFile = TemporaryFileSystem.ROOT_FOLDER + File.separator + "log.txt";
        fileSystem = new TemporaryFileSystem();
        fileSystem.makeFolder("1");
        fileSystem.makeFolder("1" + File.separator + "2");
        fileSystem.makeFolder("1" + File.separator + "2" + File.separator + "3");
        fileSystem.makeFile(String.join(File.separator, "1", "2", "3"), "3.xml");
    }

    @Test
    public void whenSearchCommandExecutedThanResultFileShouldAppear() {
        String[] args = {"-d", TemporaryFileSystem.ROOT_FOLDER, "-n", "*.pdf", "-m", "-o", resultFile};
        new FileSearch().search(args);
        assertTrue(new File(resultFile).exists());
    }

    @Test
    public void whenFilesNotFoundThanResultFileShouldBeEmpty() {
        String[] args = {"-d", TemporaryFileSystem.ROOT_FOLDER, "-n", "*.pdf", "-m", "-o", resultFile};
        new FileSearch().search(args);
        assertEquals("", getFileContent());
    }

    @Test
    public void whenFileIsFoundThanResultFileShouldContainFilePath() {
        String[] args = {"-d", TemporaryFileSystem.ROOT_FOLDER, "-n", "3.xml", "-m", "-o", resultFile};
        new FileSearch().search(args);
        var resultFilePath = String.join(File.separator,
                TemporaryFileSystem.ROOT_FOLDER, "1", "2", "3", "3.xml");
        assertEquals(resultFilePath, getFileContent());
    }

    @Test
    public void whenArgumentInputIsWrongThanResultFileShouldNotAppear() {
        String[] args = {"-d", TemporaryFileSystem.ROOT_FOLDER, "-n", "*.pdf", "-m", "-o"};
        new FileSearch().search(args);
        assertFalse(new File(resultFile).exists());
    }

    @After
    public void tearDown() throws IOException {
        fileSystem.removeRootFolder();
    }

    private String getFileContent() {
        var resultFile = new File(this.resultFile);
        var result = "";
        try (BufferedReader bf = new BufferedReader(new FileReader(resultFile))) {
            result = bf.lines().reduce("", String::concat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}