package ru.job4j.commandoptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandOptionTest {

    @Test
    public void testWhenDefaultOptionParametersSetValueShouldReturnTrue() {
        var option = new CommandOption("-k", "description", false);
        var result = option.setValue("value");
        assertTrue(result);
    }

    @Test
    public void testWhenGettersReturnCorrectValues() {
        var option = new CommandOption("-k", "description", false);
        option.setValue("value");
        assertFalse(option.isRequired());
        assertEquals("description", option.getDescription());
        assertEquals("value", option.getValue());
        assertEquals("-k", option.getKey());
    }

    @Test
    public void testWhenSetEmptyValueWithDefaultValuePredicateShouldReturnFalse() {
        var option = new CommandOption("-k", "description", false);
        var result = option.setValue("");
        assertFalse(result);
    }

    @Test
    public void testSetValueWithCustomValuePredicate() {
        var option = new CommandOption(
                "-k",
                "description",
                false,
                s -> s.length() > 1);
        var result = option.setValue("1");
        assertFalse(result);
        result = option.setValue("12");
        assertTrue(result);
    }

    @Test
    public void testWhenSetValueWithDefaultValueParserShouldReturnSameValue() {
        var option = new CommandOption("-k", "description", false);
        option.setValue("1");
        assertEquals("1", option.getValue());
    }

    @Test
    public void testWhenSetValueWithCustomValueParserShouldReturnParsedValue() {
        var option = new CommandOption(
                "-k",
                "description",
                false,
                CommandOption::defaultPredicate,
                s -> s.substring(1));
        option.setValue("12");
        assertEquals("2", option.getValue());
    }
}
