package ru.job4j.commandoptions;

import java.util.function.Function;
import java.util.function.Predicate;

public class CommandOption {
    private final String key;
    private String value;
    private final String description;
    private final boolean required;
    private boolean present = false;
    private Predicate<String> valuePredicate = CommandOption::defaultPredicate;
    private Function<String, String> valueParser = s -> s;

    public CommandOption(String key, String description, boolean required, Predicate<String> valuePredicate, Function<String, String> valueParser) {
        this(key, description, required, valuePredicate);
        this.valueParser = valueParser;
    }

    public CommandOption(String key, String description, boolean required, Predicate<String> valuePredicate) {
        this(key, description, required);
        this.valuePredicate = valuePredicate;
    }

    public CommandOption(String key, String description, boolean required) {
        this.key = key;
        this.description = description;
        this.required = required;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isPresent() {
        return present;
    }

    public String getValue() {
        return value;
    }

    public boolean setValue(String value) {
        var result = false;
        if (valuePredicate.test(value)) {
            this.value = valueParser.apply(value);
            result = true;
        }
        return result;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public static boolean defaultPredicate(String value) {
        return !value.isEmpty();
    }
}