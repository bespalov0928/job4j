package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenDrawSquare() {
        Shape triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(new StringBuilder()
                        .append("+").append(System.lineSeparator())
                        .append("+ +").append(System.lineSeparator())
                        .append("+  +").append(System.lineSeparator())
                        .append("+   +").append(System.lineSeparator())
                        .append("+  +").append(System.lineSeparator())
                        .append("+ +").append(System.lineSeparator())
                        .append("+")
                        .toString()
                )
        );
    }
}
