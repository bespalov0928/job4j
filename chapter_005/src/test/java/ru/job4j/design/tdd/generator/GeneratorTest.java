package ru.job4j.design.tdd.generator;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

@Ignore
public class GeneratorTest {
    private final Generator generator = (template, args) -> null;

    @Test
    public void whenDataIsCorrectThanGeneratorShouldProduceCorrectString() {
        var result = generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr Arsentev", "subject", "you"));
        assertEquals("I am a Petr Arsentev, Who are you? ", result);
    }

    @Test(expected = IllegalStateException.class)
    public void whenTemplateHaveKeysWhichNotPresentInMapThanGeneratorShouldThrowException() {
        generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr Arsentev", "subject1", "you"));
    }

    @Test(expected = IllegalStateException.class)
    public void whenMapHaveMoreKeysThanGeneratorShouldThrowException() {
        generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr Arsentev", "subject", "you", "subject1", "you"));
    }

    @Test
    public void whenTemplateHaveNoKeysThanShouldNotHaveKeys() {
        var result = generator.produce("I am, Who are? ", Map.of());
        assertEquals("I am, Who are? ", result);
    }

    @Test(expected = IllegalStateException.class)
    public void whenTemplateHaveNoKeysAndMapHaveThanGeneratorShouldThrowException() {
        generator.produce("I am, Who are? ", Map.of("name", "Petr Arsentev"));
    }
}