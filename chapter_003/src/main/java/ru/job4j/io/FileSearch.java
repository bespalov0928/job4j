package ru.job4j.io;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

public class FileSearch {

    public List<File> files(String path, FileFilter fileFilter) {
        List<File> result = new ArrayList<>();
        Queue<File> directoryQueue = new LinkedList<>();
        directoryQueue.offer(new File(path));
        while (!directoryQueue.isEmpty()) {
            File dir = directoryQueue.poll();
            for (var file : dir.listFiles(fileFilter)) {
                if (file.isFile()) {
                    result.add(file);
                } else if (file.isDirectory()) {
                    directoryQueue.offer(file);
                }
            }
        }
        return result;
    }

    public List<File> files(String path, List<String> extensions) {
        List<File> result = new ArrayList<>();
        Queue<File> directoryQueue = new LinkedList<>();
        directoryQueue.offer(new File(path));
        while (!directoryQueue.isEmpty()) {
            File dir = directoryQueue.poll();
            for (var file : dir.listFiles()) {
                if (file.isFile()) {
                    if (isCorrectExtension(file.getName(), extensions)) {
                        result.add(file);
                    }
                } else if (file.isDirectory()) {
                    directoryQueue.offer(file);
                }
            }
        }
        return result;
    }

    private boolean isCorrectExtension(String fileName, List<String> extensions) {
        var result = false;
        for (var extension : extensions) {
            if (fileName.endsWith(extension)) {
                result = true;
                break;
            }
        }
        return result;
    }
}