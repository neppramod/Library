package pr.lib.ds.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Double ended queue. Supports adding and removing from front or back.
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {
        Node prev;
        Node next;
        Item item;
        public Node(Item item) {
            this.item = item;
        }
    }

    private Node first;
    private Node last;
    private int count;

    // construct an empty deque
    public Deque() {
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }

        Node prevFirst = first;
        first = new Node(item);
        first.next = prevFirst;
        if (prevFirst != null) {
            prevFirst.prev = first;
        }

        count++;

        if (last == null) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        Node prevLast = last;
        last = new Node(item);
        last.prev = prevLast;

        if (prevLast != null) {
            prevLast.next = last;
        }

        count++;

        if (first == null) {
            first = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = (Item)first.item;
        Node secondNode = first.next;

        if (secondNode != null) {
            secondNode.prev = null;
        }

        first = first.next;
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = (Item) last.item;
        Node secondLast = last.prev;

        if (secondLast != null) {
            secondLast.next = null;
        }

        last = last.prev;
        count--;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            if (current == null) {
                throw new NoSuchElementException("No more element to iterate");
            }

            Item item = (Item) current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported in iterator");
        }
    }
}
