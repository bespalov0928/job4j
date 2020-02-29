package ru.job4j.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class ChainedFileFilter implements FileFilter {
    private final List<FileFilter> filters = new ArrayList<>();

    @Override
    public boolean accept(File pathname) {
        var result = false;
        for (var filter : filters) {
            if (filter.accept(pathname)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void add(FileFilter fileFilter) {
        filters.add(fileFilter);
    }
}
