package ru.job4j.zip;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static List<File> seekBy(String root, Predicate<File> predicate) {
        Queue<File> queue = new LinkedList<>(Arrays.asList(new File(root)));
        List<File> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            File dir = queue.poll();
            File[] files = dir.listFiles();
            if (files == null) {
                result.add(dir);
                continue;
            }
            var filesList = Stream.of(files).filter(predicate).collect(Collectors.toList());
            if (filesList.size() == 0) {
                result.add(dir);
            }
            for (var file : filesList) {
                if (file.isDirectory()) {
                    queue.offer(file);
                } else {
                    result.add(file);
                }
            }
        }
        return result;
    }

    private static void pack(List<File> sources, File target) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target));
        var targetPathLength = target.getAbsolutePath().length();
        for (File file : sources) {
            String filePath = file.getAbsolutePath().substring(targetPathLength + 1);
            if (file.isFile()) {
                zos.putNextEntry(new ZipEntry(filePath));
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                zos.write(bis.readAllBytes());
            } else {
                zos.putNextEntry(new ZipEntry(filePath + File.separator));
            }
            zos.closeEntry();
        }
        zos.close();
    }

    private static Predicate<File> getSearchConditions(Args arguments) {
        Predicate<File> predicate = file -> arguments.exclude().isEmpty();
        if (!arguments.exclude().isEmpty()) {
            predicate = predicate.or(file -> !file.getName().endsWith(arguments.exclude()));
        }
        return predicate;
    }

    public static void main(String[] args) {
        var arguments = new Args(args);
        var directory = arguments.directory();
        var output = arguments.output();

        var error = false;

        if (directory.isEmpty()) {
            System.out.println("Set directory you want to archive!");
            error = true;
        }

        if (output.isEmpty()) {
            System.out.println("Set directory output file name!");
            error = true;
        }

        if (!error) {
            Predicate<File> predicate = getSearchConditions(arguments);
            List<File> files = seekBy(directory, predicate);
            try {
                pack(files, new File(output));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}