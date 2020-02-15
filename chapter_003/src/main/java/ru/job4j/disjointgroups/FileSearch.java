package ru.job4j.disjointgroups;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileSearch {

    public List<File> files(String path, List<String> extensions) {
        List<File> result = new ArrayList<>();
        Queue<File> directoryQueue = new LinkedList<>();
        directoryQueue.offer(new File(path));
        while (!directoryQueue.isEmpty()) {
            File dir = directoryQueue.poll();
            for (var file : dir.listFiles()) {
                if (file.isFile()) {
                    if (extensions.contains(getFileExtension(file.getName()))) {
                        result.add(file);
                    }
                } else if (file.isDirectory()) {
                    directoryQueue.offer(file);
                }
            }
        }
        return result;
    }

    private String getFileExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}