package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {
    @Test
    public void whenFirstMax() {
        SqMax check = new SqMax();
        int result = check.max(4, 1, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenSecondMax() {
        SqMax check = new SqMax();
        int result = check.max(1, 4, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenThirdMax() {
        SqMax check = new SqMax();
        int result = check.max(2, 1, 4, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenFourthMax() {
        SqMax check = new SqMax();
        int result = check.max(3, 1, 2, 4);
        assertThat(result, is(4));
    }
}
