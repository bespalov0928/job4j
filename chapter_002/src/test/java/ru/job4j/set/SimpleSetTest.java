package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    @Test
    public void whenAddingSameElementTwiceSetSizeShouldBeOne() {
        SimpleSet<Integer> set = new SimpleSet<>(10);
        set.add(1);
        set.add(1);
        assertThat(set.getSize(), is(1));
    }
}
