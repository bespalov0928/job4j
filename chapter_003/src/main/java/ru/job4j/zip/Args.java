package ru.job4j.zip;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Args {
    private static final String DIRECTORY_OPTION = "-d";
    private static final String EXCLUDE_OPTION = "-e";
    private static final String OUTPUT_OPTION = "-o";

    private List<String> args;

    public Args(String[] args) {
        this.args = Arrays.asList(args);
    }

    public String directory() {
        return getOptionValue(DIRECTORY_OPTION);
    }

    public String exclude() {
        var exclude = getOptionValue(EXCLUDE_OPTION);
        if (exclude.startsWith("*")) {
            exclude = exclude.substring(exclude.indexOf('.') + 1);
        }
        return exclude;
    }

    public String output() {
        return getOptionValue(OUTPUT_OPTION);
    }

    private String getOptionValue(String option) {
        String result = "";
        Iterator<String> it = args.iterator();
        while (it.hasNext()) {
            if (option.equals(it.next()) && it.hasNext()) {
                result = it.next();
                break;
            }
        }
        return result;
    }
}
