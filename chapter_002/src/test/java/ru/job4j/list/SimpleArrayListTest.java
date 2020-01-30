package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.SimpleArray;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeletedFirstElementSecondBecomeFirst() {
        list.delete();
        assertThat(list.get(0), is(2));
    }

    @Test
    public void whenDeleteShouldReturnFirstElementIfNotEmpty() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        list.add(1);
        Integer deleted = list.delete();
        assertThat(deleted, is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteShouldThrowExceptionWhenEmpty() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        list.delete();
    }
}
