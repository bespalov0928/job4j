package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenMax1To2Then2() {
        Max comparator = new Max();
        int result = comparator.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax2To1Then2() {
        Max comparator = new Max();
        int result = comparator.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax2To2Then2() {
        Max comparator = new Max();
        int result = comparator.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax1To2To3Then3() {
        Max comparator = new Max();
        int result = comparator.max(1, 2, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenMax4To2To3To1Then4() {
        Max comparator = new Max();
        int result = comparator.max(4, 2, 3, 1);
        assertThat(result, is(4));
    }
}
