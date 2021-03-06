package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int size = 1;

    public Tree(E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentNode = findBy(parent);
        Optional<Node<E>> childNode = findBy(child);
        if (parentNode.isPresent() && childNode.isEmpty()) {
            parentNode.get().add(new Node<>(child));
            size++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean rsl = true;
        TreeIterator it = new TreeIterator();
        while (it.hasNext()) {
            Node<E> node = it.next();
            if (node.leaves().size() > 2) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private TreeIterator treeIt = new TreeIterator();

            @Override
            public boolean hasNext() {
                return treeIt.hasNext();
            }

            @Override
            public E next() {
                return treeIt.next().getValue();
            }
        };
    }

    private class TreeIterator implements Iterator<Node<E>> {
        private Queue<Node<E>> items = new LinkedList<>();

        public TreeIterator() {
            items.offer(root);
        }

        @Override
        public boolean hasNext() {
            return !items.isEmpty();
        }

        @Override
        public Node<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = items.poll();
            for (Node<E> leaf : node.leaves()) {
                items.offer(leaf);
            }
            return node;
        }
    }
}
