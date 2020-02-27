package ru.job4j.io;

import java.io.File;
import java.io.FileFilter;

public class ExcludeFileFilter implements FileFilter {
    private final String exclude;

    public ExcludeFileFilter(String exclude) {
        this.exclude = exclude;
    }

    @Override
    public boolean accept(File pathname) {
        return exclude.isEmpty() || !pathname.getName().endsWith(exclude);
    }
}
