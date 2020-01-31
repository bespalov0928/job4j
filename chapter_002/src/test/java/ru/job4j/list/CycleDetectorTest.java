package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ru.job4j.list.LinkedList.Node;

public class CycleDetectorTest {
    @Test
    public void whenListIsEmptyShouldReturnFalse() {
        assertFalse(CycleDetector.hasCycle(null));
    }

    @Test
    public void whenListOneElementHaveNoCycleShouldReturnFalse() {
        Node<Integer> node = new Node<>(1);
        assertFalse(CycleDetector.hasCycle(node));
    }

    @Test
    public void whenElementHaveLinkOnItselfShouldReturnTrue() {
        Node<Integer> node = new Node<>(1);
        node.next = node;
        assertTrue(CycleDetector.hasCycle(node));
    }

    @Test
    public void whenElementHaveLinkOnFirstShouldReturnTrue() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        node.next = node2;
        node2.next = node;
        assertTrue(CycleDetector.hasCycle(node));
    }

    @Test
    public void whenElementHaveLinkOnMiddleShouldReturnTrue() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        assertTrue(CycleDetector.hasCycle(node));
    }
}
