package ru.job4j.shell;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {
    private Path path;

    public Shell() {
        path = Path.of(File.separator);
    }

    Shell cd(final String path) {
        if (path.startsWith("//")) {
            this.path = Paths.get(File.separator + path.replaceAll(File.separator, ""));
        } else {
            this.path = Paths.get(this.path + File.separator + path);
        }
        return this;
    }

    public String path() {
        return path.normalize().toString();
    }
}
