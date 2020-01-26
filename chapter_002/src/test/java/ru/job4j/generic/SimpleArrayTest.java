package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void addThrowsExceptionWhenArrayIsFull() {
        SimpleArray<Integer> integers = new SimpleArray<>(0);
        integers.add(0);
    }

    @Test
    public void whenAddSizeShouldGrows() {
        SimpleArray<Integer> integers = new SimpleArray<>(2);
        assertThat(integers.length(), is(0));
        integers.add(0);
        assertThat(integers.length(), is(1));
    }

    @Test
    public void getShouldReturnCorrectElementByIndex() {
        SimpleArray<Integer> integers = new SimpleArray<>(2);
        integers.add(1);
        integers.add(2);
        assertThat(integers.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getShouldThrowExceptionIfIndexOutOfBoundsIfEmpty() {
        SimpleArray<Integer> integers = new SimpleArray<>(0);
        integers.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getShouldThrowExceptionIfIndexOutOfBoundsIfNotEmpty() {
        SimpleArray<Integer> integers = new SimpleArray<>(1);
        integers.add(1);
        integers.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setShouldThrowExceptionIfIndexOutOfBounds() {
        SimpleArray<Integer> integers = new SimpleArray<>(0);
        integers.set(0, 1);
    }

    @Test
    public void setShouldChangeElement() {
        SimpleArray<Integer> integers = new SimpleArray<>(1);
        integers.add(1);
        integers.set(0, 2);
        assertThat(integers.get(0), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeShouldThrowExceptionIfIndexOutOfBounds() {
        SimpleArray<Integer> integers = new SimpleArray<>(0);
        integers.remove(0);
    }

    @Test
    public void removeShouldDecrementLength() {
        SimpleArray<Integer> integers = new SimpleArray<>(1);
        integers.add(1);
        assertThat(integers.length(), is(1));
        integers.remove(0);
        assertThat(integers.length(), is(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void ifRemoveLastElementGetByElementIndexShouldThrowException() {
        SimpleArray<Integer> integers = new SimpleArray<>(1);
        integers.add(1);
        integers.remove(0);
        integers.get(0);
    }

    @Test
    public void afterRemoveElementShouldBeMovedToTheLeft() {
        SimpleArray<Integer> integers = new SimpleArray<>(2);
        integers.add(1);
        integers.add(2);
        integers.remove(0);
        assertThat(integers.get(0), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorShouldReturnElementsSequentially() {
        SimpleArray<Integer> integers = new SimpleArray<>(2);
        integers.add(1);
        integers.add(2);
        Iterator<Integer> it = integers.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void iteratorHasNextShouldReturnFalseIfThereIsNoNextElement() {
        SimpleArray<Integer> integers = new SimpleArray<>(0);
        Iterator<Integer> it = integers.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void iteratorHasNextShouldReturnTrueIfNextElementIsPresent() {
        SimpleArray<Integer> integers = new SimpleArray<>(1);
        integers.add(1);
        Iterator<Integer> it = integers.iterator();
        assertThat(it.hasNext(), is(true));
    }
}
