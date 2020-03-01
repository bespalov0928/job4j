package ru.job4j.command;

import ru.job4j.commandoptions.CommandOption;
import ru.job4j.commandoptions.CommandOptions;
import ru.job4j.io.ChainedFileFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileSearch {
    private static final String DIRECTORY_OPTION = "-d";
    private static final String FILENAME_OPTION = "-n";
    private static final String FILENAME_MASK_OPTION = "-m";
    private static final String FILENAME_FULL_OPTION = "-f";
    private static final String OUTPUT_OPTION = "-o";

    public static void main(String[] args) {
        CommandOptions commandOptions = new CommandOptions();

        commandOptions.add(new CommandOption(
                DIRECTORY_OPTION,
                "directory for searching",
                true,
                d -> new File(d).isDirectory()));

        commandOptions.add(new CommandOption(
                OUTPUT_OPTION,
                "file to save results",
                true));

        commandOptions.add(new CommandOption(
                FILENAME_OPTION,
                "filename to search",
                true,
                CommandOption::defaultPredicate,
                s -> s.startsWith("*") ? s.substring(1) : s));

        commandOptions.add(new CommandOption(
                FILENAME_MASK_OPTION,
                "search file by mask",
                false));

        commandOptions.add(new CommandOption(
                FILENAME_FULL_OPTION,
                "search file by name",
                false));

        if (!commandOptions.parse(args)) {
            commandOptions.printErrors();
            commandOptions.printHelp();
            return;
        }

        ChainedFileFilter fileFilter = new ChainedFileFilter();

        fileFilter.add(File::isDirectory);
        var fileName = commandOptions.getOptionValue(FILENAME_OPTION);
        if (commandOptions.getOption(FILENAME_FULL_OPTION).isPresent()) {
            fileFilter.add(f -> fileName.equals(f.getName()));
        } else if (commandOptions.getOption(FILENAME_MASK_OPTION).isPresent()) {
            fileFilter.add(f -> fileName.contains(f.getName()));
        } else {
            fileFilter.add(f -> fileName.equals(f.getName()));
        }

        var files = new ru.job4j.io.FileSearch().files(commandOptions.getOptionValue(DIRECTORY_OPTION), fileFilter);

        try (PrintWriter pw = new PrintWriter(new FileWriter(commandOptions.getOptionValue(OUTPUT_OPTION)))) {
            files.forEach(file -> pw.println(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
