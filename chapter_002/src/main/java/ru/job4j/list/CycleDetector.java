package ru.job4j.list;

import ru.job4j.list.LinkedList.Node;

public class CycleDetector {
    public static boolean hasCycle(Node<?> node) {
        boolean result = false;
        Node<?> slow = node;
        Node<?> fast = node;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}
