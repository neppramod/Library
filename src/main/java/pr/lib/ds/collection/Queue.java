package pr.lib.ds.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Single ended queue. Remove from front and add from back
 */
public class Queue<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int count;

    private static class Node<T> {
        Node next;
        T item;

        public Node (T item) {
            this.item = item;
        }
    }

    // construct an empty queue
    public Queue() {
        count = 0;
        first = null;
        last = null;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the queue
    public int size() {
        return count;
    }

    // add the item to the last
    public void enque(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }

        Node prevLast = last;
        last = new Node(item);

        if (prevLast != null) {
            prevLast.next = last;
        }

        count++;

        if (isEmpty()) {
            first = last;
        }
    }

    // remove and return the item from the front
    public T deque() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        T item = (T)first.item;
        first = first.next;
        count--;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       for (T item : this) {
           sb.append(item).append(' ');
       }
       return sb.toString();
    }

    // return item from front but do not remove it
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return (T) first.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
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
