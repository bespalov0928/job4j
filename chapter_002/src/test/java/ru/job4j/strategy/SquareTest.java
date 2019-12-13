package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Shape square = new Square();
        assertThat(
                square.draw(),
                is(new StringBuilder()
                        .append("++++").append(System.lineSeparator())
                        .append("+  +").append(System.lineSeparator())
                        .append("+  +").append(System.lineSeparator())
                        .append("++++")
                        .toString()
                )
        );
    }
}
