package ru.job4j.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

@Ignore
public class FileSearchTest {
    private final String path = System.getProperty("java.io.tmpdir") + "filesearchtest";
    private final String resultFile = path + "/log.txt";

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
    public void whenSearchCommandExecutedThanResultFileShouldAppear() {
        String[] args = {"-d", path, "-n", "*.pdf", "-m", "-o", resultFile};
        new FileSearch().search(args);
        assertTrue(new File(resultFile).exists());
    }

    @Test
    public void whenFilesNotFoundThanResultFileShouldBeEmpty() {
        String[] args = {"-d", path, "-n", "*.pdf", "-m", "-o", resultFile};
        new FileSearch().search(args);
        assertEquals("", getFileContent());
    }

    @Test
    public void whenFileIsFoundThanResultFileShouldContainFilePath() {
        String[] args = {"-d", path, "-n", "3.xml", "-m", "-o", resultFile};
        new FileSearch().search(args);
        var resultFilePath = path + "/1/2/3/3.xml";
        assertEquals(resultFilePath, getFileContent());
    }

    @Test
    public void whenArgumentInputIsWrongThanResultFileShouldNotAppear() {
        String[] args = {"-d", path, "-n", "*.pdf", "-m", "-o"};
        new FileSearch().search(args);
        assertFalse(new File(resultFile).exists());
    }

    @After
    public void tearDown() {
        var resultFile = new File(this.resultFile);
        if (resultFile.exists()) {
            resultFile.delete();
        }
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