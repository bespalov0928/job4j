package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class TemporaryFileSystem {
    public static final String ROOT_FOLDER = getTmpFolder() + File.separator + "job4j";

    public TemporaryFileSystem() throws IOException {
        if (!Files.exists(Path.of(ROOT_FOLDER))) {
            Files.createDirectory(Path.of(ROOT_FOLDER));
        }
    }

    public void removeRootFolder() throws IOException {
        if (!Files.exists(Path.of(ROOT_FOLDER))) {
            return;
        }
        Files.walk(Path.of(ROOT_FOLDER))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    public void makeFolder(String dir) throws IOException {
        if (!Files.exists(Path.of(String.join(File.separator, ROOT_FOLDER, dir)))) {
            Files.createDirectory(Path.of(String.join(File.separator, ROOT_FOLDER, dir)));
        }
    }

    public void makeFile(String dir, String fileName) throws IOException {
        if (!Files.exists(Path.of(String.join(File.separator, ROOT_FOLDER, dir, fileName)))) {
            Files.createFile(Path.of(String.join(File.separator, ROOT_FOLDER, dir, fileName)));
        }
    }

    private static String getTmpFolder() {
        var tmpDir = System.getProperty("java.io.tmpdir");
        return tmpDir.endsWith(File.separator) ? tmpDir.substring(0, tmpDir.lastIndexOf(File.separator)) : tmpDir;
    }
}
