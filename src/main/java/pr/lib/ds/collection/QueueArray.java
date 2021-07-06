package pr.lib.ds.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueArray<T> implements Iterable<T> {

    private static final int INITIAL_SIZE = 10;

    private T[] items;
    private int count;
    private int front;
    private int last;

    // construct an empty queue
    public QueueArray() {
        items = (T[]) new Object[INITIAL_SIZE];
        count = 0;
        front = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    // add the item to the last
    public void enque(T item) {
        if (item == null) {
            throw new IllegalArgumentException("input item is null");
        }

        if (count == items.length) {
            resize(items.length * 2);
        }

        items[last++] = item;
        count++;
        last = last == items.length ? 0 : last;
    }

    // remove and return the item from the front
    public T deque() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        T item = items[front];
        items[front] = null;
        front++;
        count--;

        if (count > INITIAL_SIZE && count == items.length / 4) {
            resize(items.length / 2);
        }

        front = front == items.length ? 0 : front;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return (T) items[front];
    }

    private void resize(int size) {
        T[] newItems = (T[]) new Object[size];
        for (int i = 0; i < count; i++) {
            newItems[i] = items[(i + front) % items.length];
        }

        items = newItems;
        front = 0;
        last = count;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>();
    }

    // Iterate queue from front to back
    private class ListIterator<T> implements  Iterator<T> {
        private int current;

        public ListIterator() {
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < count;
        }

        @Override
        public T next() {

            if (!hasNext()) {
                throw new NoSuchElementException("No more element to iterate");
            }

            T item = (T) items[(current + front) % items.length];
            current++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }
    }
}
