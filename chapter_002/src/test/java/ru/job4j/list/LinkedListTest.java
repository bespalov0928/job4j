package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LinkedListTest {

    @Test
    public void whenGetAfterAddShouldReturnAddedElementByZeroIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        Integer result = list.get(0);
        assertThat(result, is(1));
    }

    @Test
    public void addElementWhenInitialLengthEqualsCurrSizeArrayShouldGrow() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.getSize(), is(2));
    }

    @Test
    public void hasNextReturnsTrueWhenNextIsPresent() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void hasNextReturnsFalseWhenNextIsNotPresent() {
        LinkedList<Integer> list = new LinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextShouldThrowNoSuchElementException() {
        LinkedList<Integer> list = new LinkedList<>();
        Iterator<Integer> it = list.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationOfNextShouldThrowConcurrentModificationExceptionIfArrayWasModified() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        list.add(2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextReturnElementsSequentiallyInOrderOfAdding() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        it.next();
    }
}
