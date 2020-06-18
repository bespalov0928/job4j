package ru.job4j;

import ru.job4j.commandoptions.CommandOption;
import ru.job4j.commandoptions.CommandOptions;
import ru.job4j.wget.filedownloader.HttpFileDownloader;
import ru.job4j.wget.printer.ConsolePrinter;

import java.net.MalformedURLException;
import java.net.URL;

public class App {
    public static void main(String[] args) throws InterruptedException {
        var commandOptions = new CommandOptions();

        commandOptions.add(new CommandOption(
                "-u",
                "Url of file to download",
                true,
                s -> {
                    try {
                        new URL(s);
                        return true;
                    } catch (MalformedURLException e) {
                        return false;
                    }
                }
        ));

        commandOptions.add(new CommandOption(
                "-b",
                "Bytes per second",
                true,
                s -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
        ));

        if (!commandOptions.parse(args)) {
            commandOptions.printErrors();
            commandOptions.printHelp();
            return;
        }

        Thread downloadThread = new Thread(new HttpFileDownloader(
                commandOptions.getOptionValue("-u"),
                Integer.parseInt(commandOptions.getOptionValue("-b")),
                new ConsolePrinter()));

        downloadThread.start();
        downloadThread.join();
    }
}
