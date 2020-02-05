package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenCreateMapSizeShouldBeEqualInitialCapacity() {
        var map = new SimpleHashMap<>();
        assertThat(map.getLength(), is(map.getInitialCapacity()));
    }

    @Test
    public void whenInsertNewElementAndKeyNotPresentShouldReturnTrue() {
        var map = new SimpleHashMap<>();
        boolean result = map.insert(new Object(), new Object());
        assertTrue(result);
    }

    @Test
    public void whenInsertNewElementAndKeyNotPresentSizeShouldIncrease() {
        var map = new SimpleHashMap<>();
        var oldSize = map.getSize();
        map.insert(new Object(), new Object());
        assertTrue(map.getSize() > oldSize);
    }

    @Test
    public void whenInsertNewElementAndKeyIsPresentShouldReturnFalseAndSizeShouldNotChange() {
        var map = new SimpleHashMap<>();
        var key = new Object();
        map.insert(key, new Object());
        var oldSize = map.getSize();
        boolean result = map.insert(key, new Object());
        assertFalse(result);
        assertEquals(map.getSize(), oldSize);
    }

    @Test
    public void whenSizeEqualLengthMapShouldGrowInsertShouldReturnTrue() {
        var map = new SimpleHashMap<Integer, Object>();
        map.insert(0, new Object());
        map.insert(1, new Object());
        var oldLength = map.getLength();
        var result = map.insert(2, new Object());
        assertTrue(map.getLength() > oldLength);
        assertTrue(result);
    }

    @Test
    public void whenGetByPresentIndexShouldReturnValue() {
        var map = new SimpleHashMap<Integer, String>();
        map.insert(0, "First");
        map.insert(1, "Second");
        var result = map.get(1);
        assertThat(result, is("Second"));
    }

    @Test
    public void whenKeyHaveSameHashCodeAsPresentKeyButKeysAreNotEqualShouldReturnNull() {
        var map = new SimpleHashMap<Integer, String>();
        map.insert(0, "First");
        assertNull(map.get(2));
    }

    @Test
    public void whenIndexNotPresentGetShouldReturnNull() {
        var map = new SimpleHashMap<Integer, String>();
        assertNull(map.get(0));
    }

    @Test
    public void whenDeleteByPresentIndexShouldReturnTrueAndSizeShouldDecrease() {
        var map = new SimpleHashMap<Integer, String>();
        map.insert(0, "First");
        var oldSize = map.getSize();
        assertTrue(map.delete(0));
        assertTrue(map.getSize() < oldSize);
    }

    @Test
    public void whenKeyHaveSameHashCodeAsPresentKeyButKeysAreNotEqualDeleteShouldReturnFalse() {
        var map = new SimpleHashMap<Integer, String>();
        map.insert(0, "First");
        assertFalse(map.delete(2));
    }

    @Test
    public void whenIndexNotPresentDeleteShouldReturnFalse() {
        var map = new SimpleHashMap<Integer, String>();
        assertFalse(map.delete(0));
    }

    @Test
    public void whenEmptyHasNextShouldReturnFalse() {
        var map = new SimpleHashMap<Integer, String>();
        assertFalse(map.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyNextShouldThrowException() {
        var map = new SimpleHashMap<Integer, String>();
        map.iterator().next();
    }

    @Test
    public void whenNotEmptyHasNextShouldReturnTrue() {
        var map = new SimpleHashMap<Integer, String>();
        map.insert(0, "");
        assertTrue(map.iterator().hasNext());
    }

    @Test
    public void whenHavePairInTheFirstBucketNextShouldReturnValueOfThisPair() {
        var map = new SimpleHashMap<Integer, String>();
        map.insert(0, "Test");
        assertEquals("Test", map.iterator().next());
    }

    @Test
    public void whenNotEmptyAndHavePairNotInTheFirstBucketNextShouldReturnValueOfThisPair() {
        var map = new SimpleHashMap<Integer, String>();
        map.insert(1, "Test");
        Iterator<String> it = map.iterator();
        assertEquals("Test", it.next());
        assertFalse(it.hasNext());
    }
}
