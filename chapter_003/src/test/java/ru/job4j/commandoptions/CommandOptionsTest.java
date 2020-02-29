package ru.job4j.commandoptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandOptionsTest {

    @Test
    public void testWhenRequiredOptionNotPresentParseShouldReturnFalse() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", true));
        String[] args = {""};
        assertFalse(commandOptions.parse(args));
    }

    @Test
    public void testWhenRequiredOptionIsPresentParseShouldReturnTrue() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", true));
        String[] args = {"-k", "value"};
        assertTrue(commandOptions.parse(args));
    }

    @Test
    public void testWhenParseArgumentsThanGetShouldReturnCorrectValue() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", true));
        String[] args = {"-k", "value"};
        commandOptions.parse(args);
        assertEquals("value", commandOptions.value("-k"));
    }

    @Test
    public void testWhenOptionIsNotRequiredThanParseShouldReturnTrue() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", false));
        String[] args = {""};
        assertTrue(commandOptions.parse(args));
    }

    @Test
    public void testWhenOptionIsNotRequiredButValuePresentThanGetShouldReturnValue() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", false));
        String[] args = {"-k", "value", "-d"};
        assertTrue(commandOptions.parse(args));
        assertEquals("value", commandOptions.value("-k"));
    }

    @Test
    public void testWhenOptionIsRequiredButKeyDoubledAfterValueThanGetShouldReturnValue() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", true));
        String[] args = {"-k", "value", "-k"};
        assertTrue(commandOptions.parse(args));
        assertEquals("value", commandOptions.value("-k"));
    }

    @Test
    public void testWhenOptionIsRequiredButInsteadOfValueGoesNextOptionKeyThanParseShouldReturnFalse() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", true));
        commandOptions.add(new CommandOption("-d", "description", true));
        String[] args = {"-k", "-d"};
        assertFalse(commandOptions.parse(args));
    }

    @Test
    public void testWhenInputtedValueIsNorCorrectThanParseShouldReturnFalse() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", true));
        String[] args = {"-k", ""};
        assertFalse(commandOptions.parse(args));
    }

    @Test
    public void testWhenOptionNotRequiredButPresentThanParseShouldReturnTrue() {
        var commandOptions = new CommandOptions();
        commandOptions.add(new CommandOption("-k", "description", false));
        String[] args = {"-k"};
        assertTrue(commandOptions.parse(args));
        assertTrue(commandOptions.option("-k").isPresent());
    }
}