package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TemporaryFileSystemTest {
    private TemporaryFileSystem fileSystem;

    @Before
    public void setUp() throws IOException {
        fileSystem = new TemporaryFileSystem();
    }

    @After
    public void tearDown() throws IOException {
        fileSystem.removeRootFolder();
    }

    @Test
    public void whenObjectIsConstructedTmpFolderShouldBeExist() {
        assertTrue(new File(TemporaryFileSystem.ROOT_FOLDER).exists());
    }

    @Test
    public void whenRemoveRootFolderObtainedThanRootFolderShouldNotExist() throws IOException {
        fileSystem.removeRootFolder();
        assertFalse(Files.exists(Path.of(TemporaryFileSystem.ROOT_FOLDER)));
    }

    @Test
    public void whenCreateDirectoryThanDirectoryShouldExist() throws IOException {
        fileSystem.makeFolder("1");
        assertTrue(Files.exists(Path.of(String.join(File.separator, TemporaryFileSystem.ROOT_FOLDER, "1"))));
    }

    @Test
    public void whenCreateFileThanFFileShouldExist() throws IOException {
        fileSystem.makeFolder("1");
        fileSystem.makeFile("1", "1.txt");
        assertTrue(Files.exists(
                Path.of(
                        String.join(File.separator, TemporaryFileSystem.ROOT_FOLDER, "1", "1.txt"))));
    }
}
