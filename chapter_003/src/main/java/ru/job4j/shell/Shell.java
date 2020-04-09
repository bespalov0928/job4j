package ru.job4j.shell;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Shell {
    private Deque<String> path;

    public Shell() {
        path = new ArrayDeque<>();
    }

    Shell cd(final String path) {
        Arrays.stream(path.split(File.separator)).forEach(this::modifyPath);
        return this;
    }

    public String path() {
        return File.separator + String.join(File.separator, this.path);
    }

    private void modifyPath(final String path) {
        switch (path) {
            case "":
            case "/":
                this.path.clear();
                break;
            case ".":
                break;
            case "..":
                this.path.removeLast();
                break;
            default:
                this.path.addLast(path);
        }
    }
}
