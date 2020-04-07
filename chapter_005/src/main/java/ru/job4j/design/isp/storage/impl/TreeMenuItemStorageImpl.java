package ru.job4j.design.isp.storage.impl;

import ru.job4j.design.isp.menu.item.LevelMenuItem;
import ru.job4j.design.isp.menu.item.MenuItemImpl;
import ru.job4j.design.isp.storage.TreeMenuItemStorage;

import java.util.*;

public class TreeMenuItemStorageImpl implements TreeMenuItemStorage<LevelMenuItem> {
    private final Node root = new Node(new MenuItemImpl(""), -1);

    private final static class Node {
        private final int level;
        private final LevelMenuItem item;
        private final List<Node> children = new ArrayList<>();

        private Node(LevelMenuItem item, int level) {
            this.item = item;
            this.level = level;
        }

        public void addChild(LevelMenuItem item) {
            children.add(new Node(item, level + 1));
        }

        public LevelMenuItem getItem() {
            return item;
        }
    }

    @Override
    public void add(LevelMenuItem item, LevelMenuItem parent) {
        if (parent == null) {
            root.addChild(item);
            return;
        }
        Queue<Node> data = new LinkedList<>();
        for (var child : root.children) {
            data.offer(child);
        }
        while (!data.isEmpty()) {
            Node el = data.poll();
            if (parent.equals(el.getItem())) {
                el.addChild(item);
                break;
            }
            for (var child : el.children) {
                data.offer(child);
            }
        }
    }

    @Override
    public Iterator<LevelMenuItem> iterator() {
        return new Iterator<>() {
            private TreeIterator treeIt = new TreeIterator();

            @Override
            public boolean hasNext() {
                return treeIt.hasNext();
            }

            @Override
            public LevelMenuItem next() {
                var node = treeIt.next();
                node.item.setLevel(node.level);
                return node.item;
            }
        };
    }

    private class TreeIterator implements Iterator<Node> {
        private Deque<Node> treeNodes = new LinkedList<>();

        public TreeIterator() {
            treeNodes.addAll(root.children);
        }

        @Override
        public boolean hasNext() {
            return !treeNodes.isEmpty();
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node node = treeNodes.poll();
            var children = node.children;
            Collections.reverse(children);
            for (var child : children) {
                treeNodes.addFirst(child);
            }
            return node;
        }
    }
}
