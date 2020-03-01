package ru.job4j.commandoptions;

import java.util.*;

public class CommandOptions {
    private final static String ERROR_KEY_REQUIRED = "Option %s is required!";
    private final static String ERROR_INPUT_KEY_VALUE = "Please enter value for %s option!";
    private final static String ERROR_INPUT_INCORRECT = "Input is incorrect for option %s !";

    private final Map<String, CommandOption> optionMap = new HashMap<>();
    private final List<String> errors = new ArrayList<>();

    public void add(CommandOption option) {
        this.optionMap.put(option.getKey(), option);
    }

    public boolean parse(String[] args) {
        var input = Arrays.asList(args);
        for (var key : optionMap.keySet()) {
            var option = getOption(key);
            var index = input.indexOf(key);
            if (index < 0) {
                if (option.isRequired()) {
                    errors.add(String.format(ERROR_KEY_REQUIRED, key));
                }
                continue;
            }
            option.setPresent(true);
            if (++index == input.size()) {
                if (option.isRequired()) {
                    errors.add(String.format(ERROR_INPUT_KEY_VALUE, key));
                }
                continue;
            }
            var value = input.get(index);
            var nextOption = getOption(value);
            if (nextOption == null && !option.setValue(value)) {
                errors.add(String.format(ERROR_INPUT_INCORRECT, key));
            } else if (nextOption != null && option.isRequired()) {
                errors.add(String.format(ERROR_INPUT_KEY_VALUE, key));
            }
        }
        return errors.isEmpty();
    }

    public String getOptionValue(String key) {
        return getOption(key).getValue();
    }

    public CommandOption getOption(String key) {
        return optionMap.get(key);
    }

    public void printErrors() {
        System.out.println("Errors in command input:");
        errors.forEach(System.out::println);
    }

    public void printHelp() {
        System.out.println("Help:");
        optionMap.keySet().forEach(k -> System.out.println(String.join(" ", k, getOption(k).getDescription())));
    }
}
