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
        var input = new LinkedList<>(Arrays.asList(args));
        for (var key : optionMap.keySet()) {
            var option = optionMap.get(key);

            var index = input.indexOf(key);
            if (index < 0 && !option.isRequired()) {
                continue;
            }
            if (index < 0 && option.isRequired()) {
                errors.add(String.format(ERROR_KEY_REQUIRED, key));
                continue;
            }
            if (++index == input.size() && option.isRequired()) {
                errors.add(String.format(ERROR_INPUT_KEY_VALUE, key));
                continue;
            }

            var value = input.get(index);
            var nextCommand = optionMap.get(value);
            if (nextCommand != null && option.isRequired()) {
                errors.add(String.format(ERROR_INPUT_KEY_VALUE, key));
            } else if (!option.setValue(value)) {
                errors.add(String.format(ERROR_INPUT_INCORRECT, key));
            }
        }
        return errors.isEmpty();
    }

    public String get(String key) {
        var option = optionMap.get(key);
        return option == null ? "" : option.getValue();
    }

    public void printErrors() {
        System.out.println("Errors in command input:");
        errors.forEach(System.out::println);
    }

    public void printHelp() {
        System.out.println("Help:");
        optionMap.keySet().forEach(k -> {
            var option = optionMap.get(k);
            System.out.println(String.join(" ", k, option.getDescription()));
        });
    }
}
