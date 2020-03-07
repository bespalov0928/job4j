package ru.job4j.command;

import ru.job4j.commandoptions.CommandOption;
import ru.job4j.commandoptions.CommandOptions;
import ru.job4j.io.ChainedFileFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileSearch {
    private static final String DIRECTORY_OPTION = "-d";
    private static final String FILENAME_OPTION = "-n";
    private static final String FILENAME_MASK_OPTION = "-m";
    private static final String FILENAME_FULL_OPTION = "-f";
    private static final String OUTPUT_OPTION = "-o";

    private final CommandOptions commandOptions = new CommandOptions();
    private final ChainedFileFilter fileFilter = new ChainedFileFilter();

    private void setCommandOptions() {
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
    }

    private void setFileFilters() {
        fileFilter.add(File::isDirectory);
        var fileName = commandOptions.getOptionValue(FILENAME_OPTION);
        if (commandOptions.getOption(FILENAME_FULL_OPTION).isPresent()) {
            fileFilter.add(f -> fileName.equals(f.getName()));
        } else if (commandOptions.getOption(FILENAME_MASK_OPTION).isPresent()) {
            fileFilter.add(f -> f.getName().contains(fileName));
        } else {
            fileFilter.add(f -> fileName.equals(f.getName()));
        }
    }

    private boolean parseArgs(String[] args) {
        var result = commandOptions.parse(args);
        if (!result) {
            commandOptions.printErrors();
            commandOptions.printHelp();
        }
        return result;
    }

    private List<File> searchFiles() {
        return new ru.job4j.io.FileSearch().files(commandOptions.getOptionValue(DIRECTORY_OPTION), fileFilter);
    }

    private void saveResultToFile(List<File> files) {
        try (var pw = new PrintWriter(new FileWriter(commandOptions.getOptionValue(OUTPUT_OPTION)))) {
            files.stream().map(File::getAbsolutePath).forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(String[] args) {
        setCommandOptions();
        if (!parseArgs(args)) {
            return;
        }
        setFileFilters();
        var files = searchFiles();
        saveResultToFile(files);
    }

    public static void main(String[] args) {
        new FileSearch().search(args);
    }
}
