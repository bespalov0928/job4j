package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayListTest {

    @Test
    public void whenGetAfterAddShouldReturnAddedElementByZeroIndex() {
        ArrayList<Integer> list = new ArrayList<>(2);
        list.add(1);
        Integer result = list.get(0);
        assertThat(result, is(1));
    }

    @Test
    public void addElementWhenInitialLengthEqualsCurrSizeArrayShouldGrow() {
        ArrayList<Integer> list = new ArrayList<>(1);
        list.add(1);
        list.add(2);
        assertThat(list.getSize(), is(2));
    }

    @Test
    public void hasNextReturnsTrueWhenNextIsPresent() {
        ArrayList<Integer> list = new ArrayList<>(1);
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void hasNextReturnsFalseWhenNextIsNotPresent() {
        ArrayList<Integer> list = new ArrayList<>(1);
        Iterator<Integer> it = list.iterator();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextShouldThrowNoSuchElementException() {
        ArrayList<Integer> list = new ArrayList<>(1);
        Iterator<Integer> it = list.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationOfNextShouldThrowConcurrentModificationExceptionIfArrayWasModified() {
        ArrayList<Integer> list = new ArrayList<>(1);
        list.add(1);
        Iterator<Integer> it = list.iterator();
        list.add(2);
        it.next();
    }
}
