package ru.job4j.zip;

import ru.job4j.commandoptions.CommandOption;
import ru.job4j.commandoptions.CommandOptions;
import ru.job4j.io.ExcludeFileFilter;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static final String DIRECTORY_OPTION = "-d";
    private static final String EXCLUDE_OPTION = "-e";
    private static final String OUTPUT_OPTION = "-o";

    private static List<File> seekBy(String root, FileFilter fileFilter) {
        Queue<File> queue = new LinkedList<>(Arrays.asList(new File(root)));
        List<File> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            File dir = queue.poll();
            File[] files = dir.listFiles(fileFilter);
            if (files == null || files.length == 0) {
                result.add(dir);
                continue;
            }
            for (var file : files) {
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

    public static void main(String[] args) {
        CommandOptions options = new CommandOptions();

        options.add(new CommandOption(
                DIRECTORY_OPTION,
                "directory to archive",
                true,
                s -> new File(s).isDirectory()));

        options.add(new CommandOption(
                EXCLUDE_OPTION,
                "filename or mask for files to exclude",
                false,
                CommandOption::defaultPredicate,
                s -> s.startsWith("*") ? s.substring(1) : s));

        options.add(new CommandOption(
                OUTPUT_OPTION,
                "file name for zip file",
                true,
                s -> s.endsWith(".zip") && s.length() > 5));

        if (!options.parse(args)) {
            options.printErrors();
            options.printHelp();
            return;
        }

        List<File> files = seekBy(options.get(DIRECTORY_OPTION), new ExcludeFileFilter(options.get(EXCLUDE_OPTION)));
        try {
            pack(files, new File(options.get(OUTPUT_OPTION)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}