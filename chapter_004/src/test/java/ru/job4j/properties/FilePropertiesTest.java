package ru.job4j.properties;

import org.junit.Test;
import ru.job4j.properties.FileProperties;
import ru.job4j.properties.IProperties;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class FilePropertiesTest {

    @Test
    public void whenPropertyIsPresentThanGetValueShouldReturnCorrectValue() {
        IProperties properties = new FileProperties("prod.properties");
        assertEquals("root", properties.getValue("username"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPropertyIsNotPresentThanGetValueShouldThrowException() {
        IProperties properties = new FileProperties("prod.properties");
        properties.getValue("default");
    }

    @Test(expected = IllegalStateException.class)
    public void whenWrongFileNameThanExceptionShouldBeThrown() {
        new FileProperties("prod.properties1");
    }
}
