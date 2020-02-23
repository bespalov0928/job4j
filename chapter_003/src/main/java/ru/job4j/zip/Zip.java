package ru.job4j.zip;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static List<File> seekBy(String root, String exclude) {
        Queue<File> queue = new LinkedList<>(Arrays.asList(new File(root)));
        List<File> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            File dir = queue.poll();
            File[] files = dir.listFiles(file -> exclude.isEmpty() || !file.getName().endsWith(exclude));
            if (files == null || files.length == 0) {
                result.add(dir);
            } else {
                for (var file : files) {
                    if (file.isDirectory()) {
                        queue.offer(file);
                    } else {
                        result.add(file);
                    }
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
            List<File> files = seekBy(directory, arguments.exclude());
            try {
                pack(files, new File(output));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}