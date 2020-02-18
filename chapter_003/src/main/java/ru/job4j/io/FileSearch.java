package ru.job4j.io;

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